package com.twitter.server

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object FinatraApp extends FinatraServer

class FinatraServer extends HttpServer {
  override protected def configureHttp(router: HttpRouter) {
    router.add[HelloController]
    router.add[WazzupController]
  }
}

  class HelloController extends  Controller {
    get("/hello") {request: Request => "FitmanApp Hello world !"}
  }

  class WazzupController extends  Controller {
    get("/wazzup") {request: Request => "wazzup bro !!!!! @@"}
  }