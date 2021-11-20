package com.yen.urlShortener.service

import java.security.MessageDigest

import com.yen.urlShortener.common.common.reverseHashMap

/** service for app */


trait baseService {
  // attr
  val prefix:String
  var urlDict:scala.collection.mutable.Map[String, String]

  // method
  def hashUrl(url:String):Option[String]
  def listUrl():String
  def reverseHash(hashCode:String):Option[String]
}

class urlService extends baseService {
  val prefix = "https://yen.shorturl/"
  var urlDict= scala.collection.mutable.Map.empty[String,String]

  override def hashUrl(url: String): Option[String] = {
    val key = url

    if (!this.urlDict.contains(key)) {
      val value = MessageDigest.getInstance("MD5").digest(url.getBytes).toString.replace("[","")
      //val value = url.map("0123456789abcdef".indexOf(_)).reduceLeft(_ * 16 + _).toString
      this.urlDict += (key.toString -> value)
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
}




