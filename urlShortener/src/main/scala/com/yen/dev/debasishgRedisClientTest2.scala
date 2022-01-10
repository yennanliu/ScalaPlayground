package com.yen.dev

import com.yen.urlShortener.redis.RedisV2

object debasishgRedisClientTest2 extends App {

  RedisV2.putValue("mykey1", "123")

  val res1 = RedisV2.get("mykey1")
  println("res1 = " + res1)

  val res2 = RedisV2.get("gegerger")
  println("res2 = " + res2)
}
