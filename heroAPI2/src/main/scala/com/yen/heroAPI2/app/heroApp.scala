package com.yen.heroAPI2.app

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

import com.yen.heroAPI2.controller.Controller

// entry point
object App extends heroApp

class heroApp extends HttpServer{
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[Controller.hello]
  }
}
