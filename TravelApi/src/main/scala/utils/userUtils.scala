package utils

class userUtils {

  def getAllUsers():String = {
      "here is the all users : " + "aaa, bbb, ccc"
  }

  def addNewUser(userName: String, password: String): Unit = {
    // TODO : need to implement the completed functionality
    println(s"new user created!  userName =  $userName")
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
