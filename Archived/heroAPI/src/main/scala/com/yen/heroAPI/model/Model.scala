package com.yen.heroAPI.model

import scala.collection.immutable.HashMap

class Model {

}

case class raw(
              id:Int,
              name:String,
              imageUrl:String,
              profile:HashMap[String,Int]
              )