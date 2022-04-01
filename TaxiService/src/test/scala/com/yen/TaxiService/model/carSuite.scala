package com.yen.TaxiService.model

import org.scalatest.funsuite.AnyFunSuite

class carSuite extends AnyFunSuite{

  test("car model init"){
    var car1 = Car(1, Location(0,0), Location(0,0),false, 0)

    assert(car1.source == Location(0,0))
    assert(car1.destination == Location(0,0))
    assert(car1.free == false)

    car1.source = Location(1,1)
    car1.destination = Location(10,10)
    car1.free = true

    assert(car1.source == Location(1,1))
    assert(car1.destination ==  Location(10,10))
    assert(car1.free == true)
  }

}
