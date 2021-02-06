package app

import com.twitter.finagle.Server
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

import controller.AppController

object App extends Server

class Server extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[AppController.ControllerTest1]
    router.add[AppController.ControllerTest2]
    router.add[AppController.ControllerGetUserList]
  }
}