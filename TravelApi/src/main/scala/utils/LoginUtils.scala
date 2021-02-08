package utils

import ujson.IndexedValue.True

class LoginUtils {

  // TODO : fix below validation logic
  val userList = List("a", "b", "c")
  val passWordList = List("a", "b", "c")

  def Login(userId:String, password: String): Boolean = {
    if ( (! userList.contains(userId) ) || ( ! passWordList.contains(password))){
      false
    } else{
      true
    }
  }
}
