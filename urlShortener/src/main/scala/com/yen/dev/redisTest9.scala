package com.yen.dev

import com.yen.urlShortener.redis.RedisV2

object redisTest9 extends App {
  val res = RedisV2.getAllValues()
  res.foreach(println(_))
}
