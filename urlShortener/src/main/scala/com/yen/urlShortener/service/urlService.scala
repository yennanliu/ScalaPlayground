package com.yen.urlShortener.service

/** service for app */

import com.yen.urlShortener.common.Common.reverseHashMap
//import com.yen.urlShortener.redis.Redis
import com.yen.urlShortener.redis.RedisV2
import com.yen.urlShortener.common.HashFunc

trait baseService {
  // attr
  val prefix:String
  var urlDict:scala.collection.mutable.Map[String, String]
  var useRedis:Boolean

  // method
  def hashUrl(url:String):Option[String]
  def listUrl():String
  def reverseHash(hashCode:String):Option[String]
  def deleteCache(key:String):Boolean
}

class urlService extends baseService {
  val prefix = "https://yen.shorturl/"
  var urlDict= scala.collection.mutable.Map.empty[String,String]
  var useRedis=true

  override def hashUrl(url: String): Option[String] = {
    val key = url

    if (!this.urlDict.contains(key)) {

      /** hash the input url */
      val hashedURL = HashFunc.getHashVal(url)

      // TODO : optimize below
      val keyNormalized = key.split("://")(1).replace("/","")
      println(s"key = $keyNormalized, value = $hashedURL")

      this.urlDict += (keyNormalized.toString -> hashedURL)

      // send to redis
      // TODO : fix java.util.concurrent.TimeoutException: Futures timed out after [1 second]
      if(useRedis){
        //val res = Redis.putValue(keyNormalized, value)
        val res = RedisV2.putValue(keyNormalized, hashedURL)
        println("put key to Redis ... " + res)
      }
      Option(hashedURL)

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

  //TODO : fix this, use key from Redis is such key not exists in tmp dict
  override def reverseHash(hashCode: String): Option[String] = {
    val reversedUrlDict = reverseHashMap(this.urlDict)
    try{
      Option(reversedUrlDict(hashCode))
    }catch {
      case e:RuntimeException => {
        println("reverse hash failed : " + e.printStackTrace())
        Option(null)
      }
    }
  }

  // TODO : fix this
  override def deleteCache(key: String): Boolean = {
    try{
      //Redis.deleteValue(key)
      RedisV2.deleteValue(key)
      true
    }catch{
      case e:RuntimeException => {
        println(s"deleteCache failed : $e")
        false
      }
      case _:Throwable => {
        println(s"deleteCache failed")
        false
      }
    }
  }
}