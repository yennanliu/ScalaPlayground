package com.yen.TaxiService.common

import scala.collection.mutable.ListBuffer

import com.yen.TaxiService.model.{Car, Location}

/**
 *  Common utils func
 */

object Common {

  // get Manhattan distance between src and dest point
  def getDistance(src:Location, dest:Location):Int = {

    val diffX = (dest.x - src.x).abs
    val diffY = (dest.y - src.y).abs

    diffX + diffY
  }

  // init cars and their status
  def InitCars():ListBuffer[Car] = {
    // init cars
    var car1 = Car(1, Location(0,0), Location(0,0),true, 0)
    var car2 = Car(2, Location(0,0), Location(0,0),true, 0)
    var car3 = Car(3, Location(0,0), Location(0,0),true, 0)

    ListBuffer(car1, car2, car3)
  }

  def InitCarsFromConf():Array[Car] = {
    // init cars
    val carFile = "src/main/resources/car.txt"
    val carInfo = Parse.parseFile(carFile)
    val carData = carInfo.split("\n")
    val res = carData.map{
      data => {
        val attr = data.split(",")
        val sourceLocation = Location(attr(1).toInt, attr(2).toInt)
        val destLocation = Location(attr(3).toInt, attr(4).toInt)

        val tmpCar = Car(attr(0).toInt, sourceLocation, destLocation, attr(5).toBoolean, attr(6).toInt)
        tmpCar
      }
    }
    res
  }
}
