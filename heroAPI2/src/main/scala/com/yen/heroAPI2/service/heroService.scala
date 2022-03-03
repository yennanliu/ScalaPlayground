package com.yen.heroAPI2.service

import scala.collection.immutable.HashMap
import com.yen.heroAPI2.model.hero

import scala.collection.mutable.ListBuffer

class heroService extends baseService{
  // attr
  // TODO : fix below hardcode data
  val hero1 = new hero(1,"Daredevil","http://i.annihil.us/u/prod/marvel/i/mg/6/90/537ba6d49472b/standard_xlarge.jpg",HashMap("str" -> 8))
  val hero2 = new hero(2,"Thor","http://x.annihil.us/u/prod/marvel/i/mg/5/a0/537bc7036ab02/standard_xlarge.jpg",HashMap("luk" -> 9))

  override def add(id: Int, name: String): Boolean = ???

  override def read(id: Int): hero = ???

  override def update(id: Int): Boolean = ???

  override def delete(id: Int): Boolean = ???

  override def readAll(): ListBuffer[hero] = {
    val heroes = new ListBuffer[hero]()
    heroes += hero1
    heroes += hero2
    heroes
  }
}
