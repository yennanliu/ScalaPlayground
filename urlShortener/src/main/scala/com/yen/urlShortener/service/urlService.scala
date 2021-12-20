package com.yen.urlShortener.service

/** service for app */

import java.security.MessageDigest

import com.yen.urlShortener.common.common.reverseHashMap
import com.yen.urlShortener.redis.Redis

trait baseService {
  // attr
  val prefix:String
  var urlDict:scala.collection.mutable.Map[String, String]
  var sendToRedis:Boolean

  // method
  def hashUrl(url:String):Option[String]
  def listUrl():String
  def reverseHash(hashCode:String):Option[String]
  def deleteCache(key:String)
}

class urlService extends baseService {
  val prefix = "https://yen.shorturl/"
  var urlDict= scala.collection.mutable.Map.empty[String,String]
  var sendToRedis=true

  override def hashUrl(url: String): Option[String] = {
    val key = url

    if (!this.urlDict.contains(key)) {

      val value = MessageDigest.getInstance("MD5").digest(url.getBytes).toString.replace("[","")
      //val value = url.map("0123456789abcdef".indexOf(_)).reduceLeft(_ * 16 + _).toString
      println(s"key = $key, value = $value")
      this.urlDict += (key.toString -> value)

      // send to redis
      // TODO : fix java.util.concurrent.TimeoutException: Futures timed out after [1 second]
      if(sendToRedis){
        val res = Redis.putValue(key, value)
        println("put key to Redis ... " + res)
      }
      Option(value)

    }else{
      println("url already in map, will reuse it")
      val value = this.urlDict.get(key)
      value
    }
  }

  override def listUrl(): String = {
    this.urlDict.toString()
  }

  override def reverseHash(hashCode: String): Option[String] = {
    val reversedUrlDict = reverseHashMap(this.urlDict)
    if (reversedUrlDict.contains(hashCode)){
      println("hashcode exist")
      Option(reversedUrlDict(hashCode))
    }else{
      println("reversedUrlDict list : " + reversedUrlDict)
      throw new RuntimeException("hashcode not exists")
    }
  }

  override def deleteCache(key: String): Unit = {
    try{
     Redis.deleteValue(key)
    }catch{
      case e:RuntimeException => println(s"deleteCache failed : $e")
      case _:Throwable => println(s"deleteCache failed")
    }
  }
}




