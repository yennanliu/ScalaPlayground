package com.yen.urlShortener.service

trait baseService {
  // attr
  val prefix:String
  var urlDict:scala.collection.mutable.Map[String, String]
  var useRedis:Boolean

  // method
  def hashUrl(url:String):Option[String]
  def listUrl():String
  def listValue():String
  def reverseHash(hashCode:String):Option[String]
  def deleteCache(key:String):Boolean
  def deleteAllCache():Boolean
}
