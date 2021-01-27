package dev

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object AppDev2 extends FinatraServer2


class FinatraServer2 extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[Controller03]
  }
}

// scrap controller
class Controller03 extends Controller {
  val r_github = requests.get("http://www.github.com")
  val httpStatus:String = r_github.statusCode.toString
  val httpText:String = r_github.text().toString
  println(httpStatus)
  get("/scrap/test1") {request: Request => s"http get status : $httpStatus | text : $httpText"}
}