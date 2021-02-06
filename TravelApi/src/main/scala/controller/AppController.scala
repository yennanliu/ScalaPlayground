package controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

import utils.userUtils

object AppController {

  class ControllerTest1 extends Controller {
    get("/test1") {request: Request => "hello from FinatraServer !!!"}
  }

  class ControllerTest2 extends Controller {
    get("/test2") {request: Request => "xxxxx"}
  }

  class ControllerGetUserList extends Controller {
    val u_utils = new userUtils
    get("/users") {request: Request =>  u_utils.getAllUsers()}
  }
}
