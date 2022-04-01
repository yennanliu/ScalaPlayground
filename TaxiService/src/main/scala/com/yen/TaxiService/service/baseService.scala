package com.yen.TaxiService.service

import com.yen.TaxiService.model.{Location, bookResponse}

/**
 *  Service trait
 */

trait baseService {
  // attr

  // method
  def book(src: Location, dest: Location):bookResponse
  def checkNearest(src:Location):Int
  def listAll():String
  def reset():Unit
  def updateStatus():Unit
  def tick():Int
}
