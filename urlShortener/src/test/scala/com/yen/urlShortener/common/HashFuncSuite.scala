package com.yen.urlShortener.common

import org.scalatest.funsuite.AnyFunSuite

class HashFuncSuite extends AnyFunSuite{

  test("test hash func : getHashVal"){
    val myUrl1 = "google.com"
    val myUrl1Hashed = HashFunc.getHashVal(myUrl1)

    assert (myUrl1Hashed.length >= 7) // sth like this "6e4784bc"

    val myUrl2 = "https://github.com/apache/spark"
    val myUrl2Hashed = HashFunc.getHashVal(myUrl2)
    
    assert (myUrl2Hashed.length >= 7)
  }
}
