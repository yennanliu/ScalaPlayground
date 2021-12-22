package com.yen.urlShortener.service

/** service for app */

import java.security.MessageDigest

import com.yen.urlShortener.common.common.reverseHashMap
import com.yen.urlShortener.redis.Redis

trait baseService {
  // attr
  val prefix:String
  var urlDict:scala.collection.mutable.Map[String, String]
  var useRedis:Boolean

  // method
  def hashUrl(url:String):Option[String]
  def listUrl():String
  def reverseHash(hashCode:String):Option[String]
  def deleteCache(key:String)
}

class urlService extends baseService {
  val prefix = "https://yen.shorturl/"
  var urlDict= scala.collection.mutable.Map.empty[String,String]
  var useRedis=true

  override def hashUrl(url: String): Option[String] = {
    val key = url

    if (!this.urlDict.contains(key)) {

      val value = MessageDigest.getInstance("MD5").digest(url.getBytes).toString.replace("[","")
      //val value = url.map("0123456789abcdef".indexOf(_)).reduceLeft(_ * 16 + _).toString

      // TODO : optimize below
      val keyNormalized = key.split("://")(1).replace("/","")

      println(s"key = $keyNormalized, value = $value")
      this.urlDict += (keyNormalized.toString -> value)

      // send to redis
      // TODO : fix java.util.concurrent.TimeoutException: Futures timed out after [1 second]
      if(useRedis){
        val res = Redis.putValue(keyNormalized, value)
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
    if (useRedis){
      // TODO : fix to read from Redis
      this.urlDict.keys.foreach(println(_))
      this.urlDict.keys.toString()
    }else{
      this.urlDict.keys.foreach(println(_))
      this.urlDict.keys.toString()
    }
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

  // TODO : fix this
  override def deleteCache(key: String): Unit = {
    try{
     Redis.deleteValue(key)
    }catch{
      case e:RuntimeException => println(s"deleteCache failed : $e")
      case _:Throwable => println(s"deleteCache failed")
    }
  }
}