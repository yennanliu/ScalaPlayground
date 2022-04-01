package com.yen.TaxiService.service

import scala.collection.mutable.ListBuffer

import com.yen.TaxiService.model.{Location, bookResponse}
import com.yen.TaxiService.common.Common.{InitCars, getDistance}

/**
 *  Taxi booking service
 */

class bookService extends baseService {

  // init time
  var total_time = 0
  // init cars
  var cars = InitCars()

  // taxi booking implementation
  override def book(src: Location, dest: Location):bookResponse = {
    try{
      // TODO : optimize below
      val carID = checkNearest(src)
      println(">>> carID = " + carID)
      // update taxi status (nearest) and update its status to "booked"
      carID match {
        case _ if carID > 0 => {
          val tmpID = carID-1
          val tmpCar = cars(tmpID)
          tmpCar.destination = dest
          tmpCar.free = false
          this.cars(tmpID) = tmpCar
          println(s"car ${tmpCar.id} is booked ! : ${tmpCar.toString}")
          // travel time from car init location to booking start location
          val travel_time_car_src = getDistance(tmpCar.source, src)
          // travel time from booking start location to booking end location
          val travel_time_src_dest = getDistance(src, tmpCar.destination)
          bookResponse(carID, travel_time_car_src + travel_time_src_dest)
        }
        // if there is no available taxi
        case _ => {
          println("no available car")
          bookResponse(0, this.total_time)
        }
      }
    }catch{
      // exception handling
      case e:RuntimeException => {
        e.printStackTrace()
        bookResponse(0, this.total_time)
      }
    }
  }

  // check nearest available taxi
  override def checkNearest(expectedSrc:Location):Int = {
    // get distance from all cars with src
    var res:ListBuffer[(Int,Int)] = cars.map{
          car => {
            car.free match {
              case _ if car.free == true => {
                val dist = getDistance(car.source, expectedSrc)
                (car.id, dist.toInt)
              }
              // if there is no available taxi
              case _ => (0,0)
            }
          }
    }.filter(x => x._1 > 0).sortBy(_._1)  // filter out carID == 0, and sort by distance

    println(">>> res = " + res)

    // return valid carID only if there is  available taxi
    if (res.length > 0){
      res(0)._1
    }else{
      0
    }
  }

  // list all taxi
  override def listAll():String = {
    var res = ""
    for (car <- cars){
      res += car.toString + "\n"
    }
    res
  }

  // reset service status
  override def reset(): Unit = {
    try{
      this.cars = InitCars()
      println("reset OK")
    }catch {
      case e:RuntimeException => {
        println("reset failed")
        e.printStackTrace()
      }
    }
  }

  // update traveling taxi status
  override def updateStatus(): Unit = {
    for (car <- cars){
      if (car.free == false){
        car.travelTime = this.total_time
        println(">>> getDistance(car.source, car.destination) = " + getDistance(car.source, car.destination))
        if (car.travelTime >= getDistance(car.source, car.destination)){
          car.free = true
          car.travelTime = 0
          car.source = car.destination
          car.destination = Location(0,0)
        }
      }
    }
  }

  // implement tick (moving time forward)
  override def tick(): Int = {
    this.total_time += 1
    updateStatus()
    this.total_time
  }

}
