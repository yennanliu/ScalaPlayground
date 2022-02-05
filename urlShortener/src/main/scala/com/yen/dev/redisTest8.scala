package com.yen.dev

import com.yen.urlShortener.redis.RedisV2

object redisTest8 extends App {
  val res = RedisV2.getAllKeys()
  res.foreach(println(_))
}
