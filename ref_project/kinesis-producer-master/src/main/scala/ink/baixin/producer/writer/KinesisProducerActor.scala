package ink.baixin.producer.writer

import akka.actor.{Actor, Props, UnboundedStash}
import com.amazonaws.services.kinesis.producer.{UserRecordFailedException, UserRecordResult}
import com.fasterxml.uuid.Generators
import ink.baixin.producer.conf.{ProducerConf, ThrottlingConf}
import ink.baixin.producer.logging.Logger
import ink.baixin.producer.writer.KinesisProducerActor.{SendFailed, SendSuccessful, SendWithCallback, StopProducer}
import akka.pattern._

import scala.concurrent.Future

object KinesisProducerActor {

  private val UUID_GENERATOR = Generators.timeBasedGenerator

  def props(producerConf: ProducerConf): Props = {
    val props = Props(classOf[KinescisProducerActor], KinesisProducer(producerConf), producerConf.throttlingConf)
    // if dispatcher is empty, return props; else, return props.withDispatcher(dispatcher)
    producerConf.dispatcher.fold(props)(props.withDispatcher)
  }

  case class SendWithCallback(producerEvent: ProducerEvent,
                              messageId: String = UUID_GENERATOR.generate().toString)

  case class send(producerEvent: ProducerEvent)

  case class SendSuccessful(messageId: String, userRecordResult: UserRecordResult)

  case class SendFailed(messageId: String, producerEvent: ProducerEvent, reason: Throwable)

  case object StopProducer

}

class KinescisProducerActor(producer: KinesisProducer, throttlingConf: Option[ThrottlingConf])
  extends Actor with UnboundedStash with Logger {

  import context.dispatcher

  def receive = processing

  private def processing: Receive = {
    if (producer.isRun == true) {
      case SendWithCallback(event, messageId) => {
        val result: Future[UserRecordResult] = producer.addUserRecord(event)
        result
          .map { result =>
            logger.info("---------- Pushing New Event ----------")
            logger.info(s"Event: $event")
            SendSuccessful(messageId, result)
          }
          .recover {
            case ex: UserRecordFailedException =>
              import scala.collection.JavaConverters._
              val errorList = ex.getResult.getAttempts.asScala.map { attempt =>
                s"""
                   |Delay after prev attempt: ${attempt.getDelay} ms,
                   |Duration: ${attempt.getDuration} ms, Code: ${attempt.getErrorCode},
                   |Message: ${attempt.getErrorMessage}
               """.stripMargin
              }
              logger.warn(s"Record failed to put, partitionKey=${event.partitionKey}, payload=${event.payload}, attempts:$errorList", ex)
              SendFailed(messageId, event, ex)

            case ex =>
              logger.warn(s"Failed to send message to kinesis with: $event", ex)
              SendFailed(messageId, event, ex)
          }
          .pipeTo(self)
      }

      case SendSuccessful(messageId, result) =>
        logger.info(s"Send message successfully , messageId -> $messageId, result -> $result")

      case SendFailed(messageId, event, reason) =>
        logger.info(s"Send message failed, messageId -> $messageId, reason -> $reason, retry -> ${event.retryTime}")
        event.retryTime += 1
        self ! SendWithCallback(event)

      case StopProducer =>
        try {
          producer.stop
          sender ! "The producer has been stopped!"
        } catch {
          case ex: Exception =>
            logger.error(s"Stop failed, reason -> $ex")
            sender ! "Some error happened when stop the producer!"
        }

      case msg => logger.info(s"KinesisProducerActor received unexpected message $msg")
    } else {
      case _ => sender ! "Producer is not running, please start it first."
    }
  }

}