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
      val res = r.del(key)
      if (res == Some(1)){
        println(s"delete key : $key OK")
        true
      }else{
        println(s"deleteValue failed : $key")
        false
      }
    }catch {
      case r:RuntimeException => {
        println(s"deleteValue failed : $key " + r)
        false
      }
      case e:Exception => {
        println(s"deleteValue failed : $key " + e.printStackTrace())
        false
      }
    }
  }

  def getKey(key:String):Option[String]={
    r.get(key).orElse(None)
  }

}
