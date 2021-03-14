package utils

import utils.DataIO

import scala.collection.mutable.ListBuffer

class userUtils {

  val data_io = new DataIO
  val user_data = data_io.getUserRecord()
  val userIds = ListBuffer[String]()

  def getUser(userId: String): String ={
    getAllUsers()
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
      for (i <- user_data.obj.keys) {
        if (! userIds.contains(i)){
          userIds += i
        }
      }
      userIds
    }

  def addNewUser(userId: String): Unit = {
    // TODO : need to implement the completed functionality
    getAllUsers()
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