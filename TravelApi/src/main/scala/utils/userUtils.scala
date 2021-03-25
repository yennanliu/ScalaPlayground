package utils

import scala.collection.mutable.ListBuffer

class userUtils {

  val data_io = new DataIO

  def getUser(userId: String): String ={
    val userIds = getAllUsers()
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
    val user_data = data_io.getUserRecord()
    val userIds = ListBuffer[String]()
    for (i <- user_data.obj.keys) {
        if (! userIds.contains(i)){
          userIds += i
        }
      }
      userIds
    }

  def addNewUser(userId: String): Unit = {
    // TODO : need to implement the completed functionality
    val userIds = getAllUsers()
    if (userIds.contains(userId)){
      s"user already existed (userId = $userId)"
    } else {
      data_io.addUserRecord(userId)
    }
  }

  // TODO : fix this method
  def deleteUser(userId: String): Unit ={
    val userIds = getAllUsers()
    if (! userId.contains(userId)) {
      println(s"failed to delete userId = $userId ! ")
    }
    userIds -= userId
    // clean user.json
    data_io.deleteFile("src/main/scala/data/user.json")
    // save current userIds to user.json
    for (i <- userIds) {
      addNewUser(i)
    }
    println(s"delete userId = $userId ok !")
  }

  def updateUser(userId: String): Unit = {
    // TODO : need to implement the completed functionality
    println(s"user updated!  userId =  $userId")
  }

//  private def saveUsers(userIds:ListBuffer[String]): Unit = {
//  }
}