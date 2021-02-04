package dev

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.util.Promise.Responder

import Scraper.GithubScraper

object AppDev2 extends FinatraServer2

class FinatraServer2 extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[Controller2_1]
    router.add[Controller2_2]
    router.add[Controller2_3]
    router.add[Controller2_4]
    router.add[Controller2_5]
  }
}

// scrap controller
class Controller2_1 extends Controller {
  val r_github = requests.get("http://www.github.com")
  val httpStatus:String = r_github.statusCode.toString
  val httpText:String = r_github.text().toString
  println(httpStatus)
  get("/scrap2/test1") {request: Request => s"http get status : $httpStatus | text : $httpText"}
}

class Controller2_2 extends Controller {

  def ScrappingGithub(userName:String):String = {
    val baseURL = "https://github.com/"
    val url = baseURL + userName
    val tmp_r = requests.get(url)
    tmp_r.text()
  }

  //https://twitter.github.io/finatra/user-guide/http/controllers.html
  get("/scrap2/test2/:userName") {
    request: Request =>
      val userName = request.params("userName")
      println(s"**** userName = $userName")
      ScrappingGithub(userName)
  }
}

class Controller2_3 extends Controller{

  get("/scrap2/test3/:userName"){
    requests: Request =>
      val userName = requests.params("userName")
      println(s"**** userName = $userName")
      GithubScraper.ParseText(userName)
  }
}

class Controller2_4 extends Controller {
  val r_github = requests.get("https://api.github.com/events")
  val httpStatus:String = r_github.statusCode.toString
  println(httpStatus)
  // process event to json format
  val r_json = ujson.read(r_github.text)
  get("/scrap2/test4") {request: Request => r_json }
}

// http://www.lihaoyi.com/upickle/#uJson
class Controller2_5 extends Controller {
  val url = "https://api.github.com/events"
  val r_github = requests.get(url)
  val httpStatus:String = r_github.statusCode.toString
  println(httpStatus)
  // process event to json format
  val r_json = ujson.read(r_github.text)
  println("***" + r_json.arr(0).obj.keys + " id = " +  r_json(0)("id").str + " json_length = " + r_json.arr.length)
  //val ids = r_json.map( _ =)

    for (event <- r_json.arr){
      println("*** event = " + event)
      println("*** id = " + event("id") + "|  type = " + event("type"))
    }
  get("/scrap2/test5") {request: Request => r_json }
}