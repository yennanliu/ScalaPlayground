package utils

// TO FIX : load the default user data via config
//import data.userData

class userUtils {

  val user_data = new data.userData
  val userIds = user_data.userIds
  val userPasswords = user_data.userPasswords

  def getUser(userId: String): String ={
    if ( userIds.contains(userId)) {
      s"userId = $userId"
    } else {
      s"user not existed!"
    }
  }

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
