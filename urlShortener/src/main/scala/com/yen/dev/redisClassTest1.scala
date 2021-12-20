package com.yen.dev

import com.yen.urlShortener.redis.Redis

object redisClassTest1 extends App {

  val res = Redis.putValue("key_104", "value_100")
  println("res = " + res)
}