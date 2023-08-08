package ink.baixin.producer.logging

import com.typesafe.scalalogging.Logger

trait Logger {
  val logger = Logger("QueryLogger")
}
