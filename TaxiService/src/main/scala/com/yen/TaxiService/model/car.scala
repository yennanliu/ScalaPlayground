package com.yen.TaxiService.model

// model for car
case class Car(id:Int, var source:Location, var destination:Location, var free:Boolean, var travelTime: Int)
