package utils

import utils.DataIO

import scala.collection.mutable.ListBuffer

class userUtils {

  val data_io = new DataIO
  val user_data = data_io.getUserRecord()
  val userIds = ListBuffer[String]() //user_data("users")("userId").arr.map(_.str)

  // TODO : get the users id via scala static way
  def getCurrentUserids(): ListBuffer[String] = {
    //val user_data = data_io.getUserRecord()
    //val result = scala.collection.mutable.Set[String]()
    for (i <- user_data("users").obj.keys) {
      if (! userIds.contains(i)){
        userIds += i
      }
    }
    userIds
  }

  def getUser(userId: String): String ={
    getCurrentUserids()
    println("*** userIds.contains(userId) = " + userIds.contains(userId))
    if ( userIds.contains(userId) ) {
      s"userId = $userId"
    } else {
      s"""user = $userId is not existed !
        | $getAllUsers
      """.stripMargin
    }
  }

  def getAllUsers() = {
      //val user_data = data_io.getUserRecord()
      //val result = scala.collection.mutable.Set[String]()
      for (i <- user_data.obj.keys) {
        if (! userIds.contains(i)){
          userIds += i
        }
      }
      userIds
    }

  def addNewUser(userId: String): Unit = {
    // TODO : need to implement the completed functionality
    getCurrentUserids()
    if (userIds.contains(userId)){
      s"user already existed (userId = $userId)"
    } else {
      data_io.addUserRecord(userId)
      //userIds :: List(userId)
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