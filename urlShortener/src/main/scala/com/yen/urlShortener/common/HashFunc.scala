package com.yen.urlShortener.common

import java.security.MessageDigest

object HashFunc {
  def getHashVal(url:String):String = {
    val value = MessageDigest
      .getInstance("MD5")
      .digest(url.getBytes)
      .toString.replace("[","")
      .replace("@","")
      .split("B")(1)

    //val value = url.map("0123456789abcdef".indexOf(_)).reduceLeft(_ * 16 + _).toString

    value
  }
}
