package utils

// TO FIX : load the default user data via config
//import data.userData

import utils.DataIO

import scala.collection.mutable.ArrayBuffer

class userUtils {

  //  val user_data = new data.userData
  //  val userIds = user_data.userIds
  //  val userPasswords = user_data.userPasswords

  val data_io = new DataIO
  val user_data = data_io.getUserRecord()
  val userIds = user_data("userId").arr.map(_.str)

  def getUser(userId: String): String ={
    println("*** userIds.contains(userId) = " + userIds.contains(userId))
    if ( userIds.contains(userId) ) {
      s"userId = $userId"
    } else {
      s"""user = $userId is not existed !
        | existing users : $userIds
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
