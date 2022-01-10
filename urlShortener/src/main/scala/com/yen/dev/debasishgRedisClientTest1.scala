package com.yen.dev

// https://github.com/debasishg/scala-redis

import com.redis._

object debasishgRedisClientTest1 extends App {
  val r = new RedisClient("localhost", 6379)
  println(r)

  r.set("key", "some value")
  val _val = r.get("key")
  println("_val = " + _val)
}
