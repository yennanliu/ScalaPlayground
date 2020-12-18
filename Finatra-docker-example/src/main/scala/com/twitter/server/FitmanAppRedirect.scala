package com.twitter.server

// https://twitter.github.io/finatra/user-guide/http/requests.html
// https://github.com/twitter/finatra/blob/6c02166790eeb2ae32f736c7e88da7418e8f58ff/http/src/test/scala/com/twitter/finatra/http/tests/integration/doeverything/test/MaxRequestFowardingDepthTest.scala

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.routing.{HttpForward, HttpRouter}

import javax.inject.Inject

object FitmanAppRedirect extends FitmanServer5

class FitmanServer5 extends HttpServer {
  override protected def defaultHttpsPort: String = ":8080"
  override protected def defaultHttpServerName: String = "FitMan"

  override protected def configureHttp(router: HttpRouter) {
    router.add[HelloController5]
    router.add[BarController5]
    router.add[ControllerRedirect]
  }
}

class HelloController5 extends Controller {
  get("/hello") {request: Request => "FitmanApp Hello world !"}
}

class BarController5 extends Controller {
  get("/bar") {request: Request => "Redirect to /bar !!!"}
}

class ControllerRedirect @Inject()(forward: HttpForward) extends Controller {
    get("/foo") { request: Request =>
      forward(request, "/bar")
      }
}