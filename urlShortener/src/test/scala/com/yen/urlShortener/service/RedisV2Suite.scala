package com.yen.urlShortener.service

import org.scalatest.funsuite.AnyFunSuite

import com.yen.urlShortener.redis.RedisV2

class RedisV2Suite extends AnyFunSuite  {

  test("put key to redis"){
    RedisV2.putValue("testKey1","testVal1")
    val res1 = RedisV2.get("testKey1")
    assert (res1 == Some("testVal1"))

    RedisV2.deleteValue("testKey1")
  }

  test("remove key to redis"){
    RedisV2.putValue("testKey2","testVal2")
    val res = RedisV2.deleteValue("testKey2")
    assert (res == true)
    val res2 = RedisV2.get("testKey2")
    assert (res2==None)
  }
}
