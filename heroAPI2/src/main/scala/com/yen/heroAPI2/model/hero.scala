package com.yen.heroAPI2.model

import scala.collection.immutable.HashMap

case class hero(
               id:Int,
               name:String,
               imageUrl:String,
               profile:HashMap[String,Int]
             )