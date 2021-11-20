package com.yen.urlShortener.common

object common {

  // help func
  def reverseHashMap(hashMap:scala.collection.Map[String, String]): scala.collection.Map[String, String]={
    var res = scala.collection.mutable.Map(""->"")
    for ( key <- hashMap.keys){
      res += (hashMap(key) -> key)
    }
    res
  }
}
