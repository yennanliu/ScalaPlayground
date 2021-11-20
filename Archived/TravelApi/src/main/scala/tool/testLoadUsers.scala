package tool

import utils.DataIO

import scala.collection.mutable.ListBuffer

object testLoadUsers extends App {
  val data_io = new DataIO
  val user_data = data_io.getUserRecord()
  val userIds = ListBuffer[String]() //user_data("users")("userId").arr.map

  println("user_data = " + user_data)
  println("userIds = " + userIds)
  println("user_data(\"users\") = " + user_data("users"))
  println("user_data(\"users\")(\"u0001\") " + user_data("users")("u0001") )

  println("===============")

  val names = ListBuffer[String]()
  for ( i <- user_data.obj.keys ){
    println("*** " + i)
    names += i
  }

  println("===============")

  for ( (k,v) <- user_data.obj ){
    println("k,v = " + (k,v))
  }

}
