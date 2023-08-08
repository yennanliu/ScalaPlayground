package ink.baixin.producer.writer

import com.amazonaws.services.kinesis.producer.{KinesisProducerConfiguration, UserRecordResult, KinesisProducer => AWSKinesisProducer}
import ink.baixin.producer.conf.ProducerConf

import scala.concurrent.Future

object KinesisProducer {

  def apply(kplConfig: KinesisProducerConfiguration, streamName: String): KinesisProducer =
    new KinesisProducer(new AWSKinesisProducer(kplConfig), streamName)

  def apply(producerConf: ProducerConf): KinesisProducer =
    apply(producerConf.kplConfiguration, producerConf.streamName)
}

class KinesisProducer(kinesis: AWSKinesisProducer, streamName: String) {
  // mark if the producer is running
  var isRun = true

  // if the messages need to be throttled
  var isThrottle = false

  def addUserRecord(event: ProducerEvent): Future[UserRecordResult] = {
    import ink.baixin.producer.utils.FutureUtils._
    kinesis.addUserRecord(streamName, event.partitionKey, event.payload).asScalaFuture
  }

  def stop() = {
    kinesis.flushSync() // This blocks until all records are flushed
    kinesis.destroy
    isRun = false
  }
}

