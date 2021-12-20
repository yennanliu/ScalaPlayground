package com.yen.dev

import com.yen.urlShortener.service.urlService

object testUrlHash extends App {

  val url_s = new urlService()

  val my_url = "www.google.com"
  val r = url_s.hashUrl(my_url)
  println ("r = " + r)
  println(url_s.urlDict)

  println("===============")

  val my_url2 = "www.yahoo.com"
  val r2 = url_s.hashUrl(my_url2)
  println ("r = " + r2)
  println(url_s.urlDict)

  println("===============")

  val my_url3 = "www.google.com"
  val r3 = url_s.hashUrl(my_url3)
  println ("r3 = " + r3)
  println(url_s.urlDict)
}