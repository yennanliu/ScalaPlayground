package controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}

import utils.userUtils

object AppController {

  val u_utils = new userUtils

  val users = List()

  class ControllerTest1 extends Controller {
    get("/test1") {request: Request => "hello from FinatraServer !!!"}
  }

  class ControllerGetUser extends Controller {
    get("/user/:userId") {
      requests: Request =>
      val userId = requests.params("userId")
      u_utils.getUser(userId)}
  }

  class ControllerGetUserList extends Controller {
    get("/users") {request: Request =>  u_utils.getAllUsers()}
  }

  class ControllerAddNewUser extends Controller {
    get("/add_users/:userName/:password") {
      requests: Request =>
      val userName = requests.params("userName")
      val password = requests.params("password")
      u_utils.addNewUser(userName, password)
      s"new user created! $userName"
    }
  }
}
