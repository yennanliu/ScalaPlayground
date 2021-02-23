package app

import com.twitter.finagle.Server
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

import controller.AppController

object App extends Server

// end points:
// http://localhost:8888/hello
// http://localhost:8888/user/u0001
// http://localhost:8888/uses

class Server extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[AppController.ControllerTest]
    router.add[AppController.ControllerGetUser]
    router.add[AppController.ControllerGetAllUsers]
    router.add[AppController.ControllerAddNewUser]
  }
}