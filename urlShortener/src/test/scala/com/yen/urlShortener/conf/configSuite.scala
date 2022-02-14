package com.yen.urlShortener.conf

import org.scalatest.funsuite.AnyFunSuite

import com.yen.urlShortener.conf.config

class configSuite extends AnyFunSuite {

  test("test config parse"){
    val env = config.env
    val host = config.host
    val port = config.port

    assert(env == "default")
    assert(host == "localhost")
    assert(port == 6379)
  }
}
