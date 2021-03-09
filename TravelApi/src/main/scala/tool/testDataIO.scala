package tool

import utils.DataIO

import scala.collection.mutable.ListBuffer

object testDataIO extends App {
  val d = new DataIO
  val users = d.getUserRecord()
  println(users.getClass)
  println(users)
  println("***" + users("users"))
  println("***" + users("users")("u0001"))

  println(users("users").obj.keys)

  //val names =  users("users").obj.foreach( _ => _("name"))

  val names = ListBuffer[String]()

  for ( i <- users("users").obj.keys ){
    println("*** " + i)
    names += i
  }
  println("names = " + names)
  println("names list = " + names.toList)
  d.getOrderRecord()

  d.addUserRecord("u-yyy")
  println(d.getUserRecord())
}