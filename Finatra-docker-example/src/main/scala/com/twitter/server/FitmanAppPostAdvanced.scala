package com.twitter.server

// https://twitter.github.io/finatra/user-guide/http/requests.html

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

// UDF
import utils.{ForbiddenFilter, AppendToHeaderFilter}


object FitmanAppPostAdvanced extends FitmanAppPostAdvancedServer

class FitmanAppPostAdvancedServer extends HttpServer {
  override protected def defaultHttpsPort: String = ":8080"
  override protected def defaultHttpServerName: String = "FitMan"

  override protected def configureHttp(router: HttpRouter) {
    router.add[HelloController_PostAdvanced]
    router.add[PostController_PostAdvanced]
    router.add[PostController_Postbasics]
  }
}

// get endpoint
class HelloController_PostAdvanced extends Controller {
  get("/hello") {request: Request => "FitmanApp Hello world !"}
}

// post endpoint
class PostController_PostAdvanced extends  Controller {
  prefix("/1.1") {
    get("/plaintext") { _: Request =>
      "Hello, World!"
    }

    post("/foo") { _: Request =>
      "bar"
    }
    // this end point will not be allowed to visit, plz check the ForbiddenFilter class
    filter[ForbiddenFilter].get("/forbiddenByFilter") { _: Request =>
      "ok!"
    }

    filter(new AppendToHeaderFilter("appended", "1"))
      .filter(new AppendToHeaderFilter("appended", "2"))
      .get("/appendMultiplePrefixed") { request: Request =>
        request.headerMap("appended")
      }

    filter(new AppendToHeaderFilter("freestyle", "bang")) {
      get("/freestyleWithHeader") { request: Request =>
        request.headerMap("freestyle")
      }
    }
  }
}

class PostController_Postbasics extends Controller{
  post("/post_basic/plus_100") {post_value: PostValue => "----> input : " + post_value.x + ", plus 100 :  " + {post_value.x + 100}.toString}
  post("/post_basic/sum") {post_values: PostValues => "----> input : " + post_values.x +  post_values.y + ", sum :  " + {ProcessValues.get_sum(post_values.x, post_values.y)}.toString}
}

case class PostValue(x: Int)
case class PostValues(x: Int, y: Int)

object ProcessValues {
  def get_sum(x: Int, y: Int): Int = x + y
  def get_multiply(x: Int, y: Int): Int = x * y
}