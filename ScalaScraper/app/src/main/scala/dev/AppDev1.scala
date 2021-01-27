package dev

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object AppDev1 extends FinatraServer1

class FinatraServer1 extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[Controller01]
    router.add[Controller02]
  }
}

class Controller01 extends Controller {
  get("/test1") {request: Request => "hello from FinatraServer !!!"}
}

class Controller02 extends Controller {
  get("/test2") {request: Request => "xxxxx"}
}