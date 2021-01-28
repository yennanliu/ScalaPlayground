package dev

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.util.Promise.Responder

import Scraper.GithubScraper

object AppDev2 extends FinatraServer2

class FinatraServer2 extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[Controller03]
    router.add[Controller04]
    router.add[Controller05]
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

class Controller04 extends Controller {

  def ScrappingGithub(userName:String):String = {
    val baseURL = "https://github.com/"
    val url = baseURL + userName
    val tmp_r = requests.get(url)
    tmp_r.text()
  }

  //https://twitter.github.io/finatra/user-guide/http/controllers.html
  get("/scrap/test2/:userName") {
    request: Request =>
      val userName = request.params("userName")
      println(s"**** userName = $userName")
      ScrappingGithub(userName)
  }

}

class Controller05 extends Controller{

  get("/scrap/test3/:userName"){
    requests: Request =>
      val userName = requests.params("userName")
      println(s"**** userName = $userName")
      GithubScraper.ParseText(userName)
  }
}