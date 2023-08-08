package ink.baixin.producer.utils

import java.util.Properties

import com.typesafe.config.Config
import scala.collection.JavaConverters._

object TypesafeConfigExtensions {
  implicit class RichConfig(val config: Config) extends AnyVal {

    /**
      * Convert Typesafe config to Java `Properties`.
      */
    def toProperties: Properties = {
      val props = new Properties()
      config
        .entrySet()
        .asScala
        .foreach(entry => props.put(entry.getKey, entry.getValue.unwrapped().toString))
      props
    }
  }
}
