package com.yen.urlShortener.redis

// https://github.com/debasishg/scala-redis

import com.redis._

class RedisV2 {
}

object RedisV2{

  val r = new RedisClient("localhost", 6379)

  def putValue(key:String, value:String):Boolean={
    try{
      r.set(key, value)
      true
    }catch {
      case r:RuntimeException => {
        println("put key, value to redis failed : " + r)
        false
      }
      case e:Exception => {
        println("put key, value to redis failed : " + e.printStackTrace())
        false
      }
    }
  }

  def deleteValue(key:String):Boolean={
    try{
      r.del(key)
      println(s"delete key : $key OK")
      true
    }catch {
      case r:RuntimeException => {
        println("deleteValue failed : " + r)
        false
      }
      case e:Exception => {
        println("deleteValue failed : " + e.printStackTrace())
        false
      }
    }
  }

  def get(key:String):Option[String]={
    r.get(key).orElse(None)
  }

}
