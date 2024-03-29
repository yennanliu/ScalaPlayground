package com.yen.urlShortener.redis

// https://github.com/debasishg/scala-redis

import com.redis._
import com.yen.urlShortener.common.Common

class RedisV2(host:String, port:Int) {
}

object RedisV2{

  // constructor
//  def apply(host:String, port:Int)={
//    val r = new RedisClient("localhost", 6379)
//    new RedisV2("localhost", 6379)
//  }

  var r = new RedisClient("localhost", 6379)
  //var r = null

  /** method put key, value into redis */
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

  /** method delete key from redis */
  def deleteKey(key:String):Boolean={
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

  /** method delete all keys from redis */
  def deleteAllKey():Unit={
    // TODO : optimize below code
    //val key_list = r.keys().toArray
    val keys = getAllKeys()
    keys.map{
      key => try{
        r.del(key)
        println(s"delete key : $key OK")
      }catch {
        case e:Exception => {
          println(s"deleteValue failed : $key " + e.printStackTrace())
          false
        }
      }
    }
  }

  /** method get key from redis */
  def getKey(key:String):Option[String]={
    r.get(key).orElse(None)
  }

  /** method get all keys from redis */
  def getAllKeys():List[String]={
    val key_list = r.keys().toArray
    val keys = key_list(0).map{
      x => {
        val key = Common.show(x)
        key
      }
    }
    keys
  }

  /** method get all values from redis */
  def getAllValues():List[String]={
    val keys = getAllKeys()
    val values = keys.map{
      key => {
        val value = Common.show(getKey(key))
        value
      }
    }
    values
  }
}
