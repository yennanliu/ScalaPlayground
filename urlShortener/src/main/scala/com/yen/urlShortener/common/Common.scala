package com.yen.urlShortener.common

/** common functions */

object Common {

  // help func
  def reverseHashMap(hashMap:scala.collection.Map[String, String]): scala.collection.Map[String, String]={
    var res = scala.collection.mutable.Map.empty[String,String]
    for ( key <- hashMap.keys){
      res += (hashMap(key) -> key)
    }
    res
  }

  // get value from Some
  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "??"
  }

  // normalize key
  def normalizeKey(key:String):String={
    key.split("://")(1)
  }

}