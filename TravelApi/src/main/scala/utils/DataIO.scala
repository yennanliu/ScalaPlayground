package utils

// https://mungingdata.com/scala/read-write-json/
// https://github.com/lihaoyi/os-lib#getting-started

import scala.collection.mutable.ListBuffer

// TODO : make a trait, and get below classes via extend that trait
class DataIO{

  /* User IO */

  def getUserRecord() = {
    os.read
    val userJson = os.read(os.pwd/"src"/"main"/"scala"/"data"/"user.json")
    val data = ujson.read(userJson)
    data
  }

  def deleteUserRecord(userId: String): Unit = {
    os.read
    val userJson = os.read(os.pwd/"src"/"main"/"scala"/"data"/"user.json")
    val data = ujson.read(userJson)

  }

  /* Order IO */

  def getOrderRecord() = {
    os.read
    val userJson = os.read(os.pwd/"src"/"main"/"scala"/"data"/"order.json")
    val data = ujson.read(userJson)
    data
  }
}


//object test extends App {
//  val d = new DataIO
//  val users = d.getUserRecord()
//  println(users.getClass)
//  println(users)
//  println("***" + users("users"))
//  println("***" + users("users")("u0001"))
//
//  println(users("users").obj.keys)
//
//  //val names =  users("users").obj.foreach( _ => _("name"))
//
//  val names = ListBuffer[String]()
//
//  for ( i <- users("users").obj.keys ){
//    println("*** " + i)
//    names += i
//  }
//  println("names = " + names)
//  println("names list = " + names.toList)
//  d.getOrderRecord()
//}