package com.yen.heroAPI2.service

import scala.collection.mutable.ListBuffer

import com.yen.heroAPI2.model.hero

trait baseService {
  // attr

  // method
  def add(id:Int,name:String):Boolean
  def read(id:Int):hero
  def update(id:Int):Boolean
  def delete(id:Int):Boolean
  def readAll:ListBuffer[hero]
}
