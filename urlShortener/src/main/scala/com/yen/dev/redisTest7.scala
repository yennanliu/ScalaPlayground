package com.yen.dev
import com.yen.urlShortener.redis.RedisV2


object redisTest7 extends App {
  val keys = RedisV2.deleteAllKey()
  println(keys)
}
