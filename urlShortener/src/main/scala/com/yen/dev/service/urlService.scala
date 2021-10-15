package com.yen.dev.service

import java.security.MessageDigest

abstract class baseService {

  // attr
  val prefix:String
  var urlDict:scala.collection.mutable.Map[String, String]

  // method
  def hashUrl(url:String):Option[String]
}

class urlService extends baseService {

  val prefix = "https://yen.shorturl/"
  var urlDict= scala.collection.mutable.Map(""->"")

  override def hashUrl(url: String): Option[String] = {

    val key = url

    if (!this.urlDict.contains(key)) {
      val value = MessageDigest.getInstance("MD5").digest(url.getBytes).toString.replace("[","")
      //val value = url.map("0123456789abcdef".indexOf(_)).reduceLeft(_ * 16 + _).toString
      this.urlDict += (key -> value)
      Option(value)

    }else{
      println("url already in map, will reuse it")
      val value = this.urlDict.get(key)
      value
    }
  }
}




