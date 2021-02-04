package dev

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.util.Promise.Responder

import com.twitter.finatra.http.HttpServer

object AppLogin extends FinatraServerLogin

class FinatraServerLogin extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[ControllerLogin_1]
    router.add[ControllerLogin_2]
  }
}

class ControllerLogin_1 extends Controller {
  get("/login1") {request: Request => "hello from FinatraServerLogin !!!"}
}

class ControllerLogin_2 extends Controller {
  def checkLogin(user:String):Boolean = {
    if (user == "admin"){
      true } else{
      false
    }
  }
  get("/login2/:user") {request: Request =>
    val user = request.params("user")
    s"*** user : $user" + "\n" +
    s"*** login status : ${checkLogin(user)}"
  }
}

