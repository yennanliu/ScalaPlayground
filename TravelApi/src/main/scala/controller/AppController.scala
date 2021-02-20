package controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}

import utils.userUtils

object AppController {

  val u_utils = new userUtils

  val users = List()

  class ControllerTest extends Controller {
    get("/hello") {request: Request => "hello from Finatra Server !!!"}
  }

  class ControllerGetUser extends Controller {
    get("/user/:userId") {
      requests: Request =>
      val userId = requests.params("userId")
      u_utils.getUser(userId)}
  }

  class ControllerGetAllUsers extends Controller {
    get("/users") {request: Request =>  u_utils.getAllUsers()}
  }

  class ControllerAddNewUser extends Controller {
    get("/add_users/:userId") {
      requests: Request =>
      val userId = requests.params("userId")
      u_utils.addNewUser(userId)
      s"new user created! $userId"
    }
  }
}
