package com.twitter.server

// https://twitter.github.io/finatra/user-guide/http/requests.html

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object FitmanAppPost extends FitmanServer4

class FitmanServer4 extends HttpServer {
  override protected def defaultHttpsPort: String = ":8080"
  override protected def defaultHttpServerName: String = "FitMan"

  override protected def configureHttp(router: HttpRouter) {
    router.add[HelloController_1]
    router.add[PostController_1]
  }
}

// get endpoint
class HelloController_1 extends Controller {
  get("/hello") {request: Request => "FitmanApp Hello world !"}
}

// post endpoint
case class HiRequest(id: Long, name: String)

class PostController_1 extends  Controller{
  post("/hi") {hiRequest: HiRequest => "----> Hello " + hiRequest.name + " with id " +  hiRequest.id + " "}
}

