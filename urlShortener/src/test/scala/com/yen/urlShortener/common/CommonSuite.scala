package com.yen.urlShortener.common

import org.scalatest.funsuite.AnyFunSuite

class CommonSuite extends AnyFunSuite {

  test("test reverseHashMap"){

    val myMap1:scala.collection.Map[String, String] = scala.collection.Map("abc"->"123","def"->"456","ghi"->"789")
    val myMap1Rev = Common.reverseHashMap(myMap1)
    assert (myMap1Rev == Map("789" -> "ghi", "456" -> "def", "123" -> "abc"))

    val myMap2 = scala.collection.Map(""->"")
    val myMap2Rev = Common.reverseHashMap(myMap2)
    assert (myMap2Rev == Map(""->""))
  }
}
