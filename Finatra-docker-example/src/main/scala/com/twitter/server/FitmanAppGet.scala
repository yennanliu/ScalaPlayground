package com.twitter.server

// https://shekhargulati.com/2017/09/14/finatra-tutorial-building-scalable-services-the-twitter-way/
// test the API via :
// curl localhost:8080/hello
// curl localhost:8080/wazzup

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object FitmanAppGet extends FitmanServer2

class FitmanServer2 extends HttpServer {
  override protected def defaultHttpsPort: String = ":8080"
  override protected def defaultHttpServerName: String = "FitMan"

  override protected def configureHttp(router: HttpRouter) {
    router.add[HelloController2]
    router.add[WazzupController2]
  }
}

class HelloController2 extends Controller {
  get("/hello") {request: Request => "FitmanApp Hello world !"}
}

class WazzupController2 extends Controller {
  get("/wazzup") {request: Request => "wazzup bro !!!!! @@"}
}