package com.yen.TaxiService.common

import org.scalatest.funsuite.AnyFunSuite

class ParseSuite extends AnyFunSuite{
  test("parseFile"){
    val srcFile = "src/main/resources/car.txt"
    val data = Parse.parseFile(srcFile)
    assert(data == "1,0,0,0,0,true,0\n2,0,0,0,0,true,0\n3,0,0,0,0,true,0")
  }
}
