package com.yen.dev

import com.yen.urlShortener.redis.RedisV2

object debasishgRedisClientTest2 extends App {

  RedisV2.putValue("mykey1", "123")

  val res1 = RedisV2.get("mykey1")
  println("res1 = " + res1)

  val res2 = RedisV2.get("gegerger")
  println("res2 = " + res2)

  RedisV2.putValue("mykey2", "123")

  val del_res2 = RedisV2.deleteValue("mykey2")
  println("del_res2 = " + del_res2)

  val del_res3 = RedisV2.deleteValue("mykey2wrrgeger")
  println("del_res3 = " + del_res3)
}
