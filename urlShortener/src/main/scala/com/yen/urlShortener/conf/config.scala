package com.yen.urlShortener.conf

import com.typesafe.config.{Config, ConfigFactory}

object config {

  private val cfg = ConfigFactory.load()

  val env:String = if (cfg.hasPath("env")) cfg.getString("env") else "default"

  val config:Config = cfg.getConfig(env).withFallback(cfg.getConfig("default"))

  val host:String = config.getString("host")
  val port:Int = config.getInt("port")
}
