package com.yen.urlShortener.redis

import org.scalatest.funsuite.AnyFunSuite

class RedisV2Suite extends AnyFunSuite  {

  test("put key to redis"){
    RedisV2.putValue("testKey1","testVal1")
    val res1 = RedisV2.getKey("testKey1")
    assert (res1 == Some("testVal1"))

    RedisV2.deleteKey("testKey1")
  }

  test("remove key to redis"){
    RedisV2.putValue("testKey2","testVal2")
    val res = RedisV2.deleteKey("testKey2")
    assert (res == true)
    val res2 = RedisV2.getKey("testKey2")
    assert (res2==None)
  }
}
