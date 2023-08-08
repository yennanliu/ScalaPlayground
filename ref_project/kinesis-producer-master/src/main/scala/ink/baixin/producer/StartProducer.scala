package ink.baixin.producer

import akka.actor.ActorSystem
import ink.baixin.producer.conf.ProducerConf
import ink.baixin.producer.parser.UrlParser
import ink.baixin.producer.writer.KinesisProducerActor
import com.typesafe.config.ConfigFactory

object StartProducer extends App {
  val system = ActorSystem("producer-system")
  val config = ConfigFactory.load.getConfig("kinesis")
  val username = config.getString("auth.username")
  val password = config.getString("auth.password")
  implicit val producerActor = system.actorOf(KinesisProducerActor.props(ProducerConf(config)), "producer-actor")

  new UrlParser(username, password).startServer("0.0.0.0", 8080)
}
