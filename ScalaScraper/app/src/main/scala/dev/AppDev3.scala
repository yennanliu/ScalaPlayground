package dev

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.util.Promise.Responder

object AppDev3 extends FinatraServer3

class FinatraServer3 extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[Controller06]
    router.add[Controller07]
    router.add[Controller08]
  }
}

class Controller06 extends Controller {
  get("/test6") {request: Request => "hello from FinatraServer !!!"}
}

class Controller07 extends Controller {
  val url = "https://www.ptt.cc/bbs/Soft_Job/M.1612203023.A.036.html"
  val r = requests.get(url)
  get("/scrapV3/test7") {request: Request => r.text()}
}

class Controller08 extends Controller {
  val url = "https://www.ptt.cc/bbs/Soft_Job/M.1612203023.A.036.html"
  val r = requests.get(url)
  get("/scrapV3/test8") {request: Request =>  r.text()}
}

