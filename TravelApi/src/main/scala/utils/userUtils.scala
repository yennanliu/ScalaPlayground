package utils

import utils.DataIO

import scala.collection.mutable.ListBuffer

class userUtils {

  val data_io = new DataIO
  val user_data = data_io.getUserRecord()
  val userIds = ListBuffer[String]() //user_data("users")("userId").arr.map(_.str)

  // TODO : get the users id via scala static way
  for ( i <- user_data("users").obj.keys ){
    userIds += i
  }

  def getUser(userId: String): String ={
    println("*** userIds.contains(userId) = " + userIds.contains(userId))
    if ( userIds.contains(userId) ) {
      s"userId = $userId"
    } else {
      s"""user = $userId is not existed !
        | $getAllUsers
      """.stripMargin
    }
  }

  def getAllUsers():String = {
      "All users : " + userIds.toString()
  }

  def addNewUser(userId: String): Unit = {
    // TODO : need to implement the completed functionality
    if (userIds.contains(userId)){
      s"user already existed (userId = $userId)"
    } else {
      userIds :: List(userId)
      getAllUsers()
    }
  }

  def deleteUser(userId: String): Unit ={
    // TODO : need to implement the completed functionality
    println(s"user deleted!  userId =  $userId")
  }

  def updateUser(userId: String): Unit = {
    // TODO : need to implement the completed functionality
    println(s"user updated!  userId =  $userId")
  }
}