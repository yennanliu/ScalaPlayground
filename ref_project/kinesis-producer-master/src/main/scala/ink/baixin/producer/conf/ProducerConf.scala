package ink.baixin.producer.conf

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration
import com.typesafe.config.Config

import scala.concurrent.duration.{FiniteDuration, _}

object ProducerConf {

  // initial kpl, akka and throttle config
  def apply(kinesisConfig: Config,
            credentialsProvider: Option[AWSCredentialsProvider] = None): ProducerConf = {
    val producerConfig = kinesisConfig.getConfig("default-producer")

    val streamName = producerConfig.getString("stream-name")

    // get akka dispatcher config
    val dispatcher: Option[String] =
      if (producerConfig.getIsNull("akka.dispatcher"))
        None
      else {
        val dispatcherProp = producerConfig.getString("akka.dispatcher")
        if (dispatcherProp.isEmpty) None else Some(dispatcherProp)
      }

    val kplConfig = buildKPLConfig(producerConfig.getConfig("kpl"))
    val throttlingConfig = buildThrottlingConfig(producerConfig)

    ProducerConf(streamName, kplConfig, dispatcher, throttlingConfig)
  }

  private def buildKPLConfig(config: Config): KinesisProducerConfiguration = {
    import ink.baixin.producer.utils.TypesafeConfigExtensions._

    val kplProps = config.toProperties
    val kplConfig = KinesisProducerConfiguration.fromProperties(kplProps)
    kplConfig
  }

  private def buildThrottlingConfig(config: Config): Option[ThrottlingConf] = {
    if (!config.hasPath("akka.max-outstanding-requests")
      || config.getIsNull("akka.max-outstanding-requests"))
      None
    else {
      val maxOutstandingRequests = config.getInt("akka.max-outstanding-requests")
      if (!config.hasPath("akka.throttling-retry-millis")
        || config.getIsNull("akka.throttling-retry-millis"))
        Some(ThrottlingConf(maxOutstandingRequests))
      else
        Some(ThrottlingConf(maxOutstandingRequests,
          config.getLong("akka.throttling-retry-millis").millis))
    }
  }

}

final case class ProducerConf(streamName: String,
                              kplConfiguration: KinesisProducerConfiguration,
                              dispatcher: Option[String],
                              throttlingConf: Option[ThrottlingConf])

final case class ThrottlingConf(maxOutstandingRequests: Int,
                                retryDuration: FiniteDuration = 100.millis)