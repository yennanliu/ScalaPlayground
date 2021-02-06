package controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

object AppController {

  class Controller01 extends Controller {
    get("/test1") {request: Request => "hello from FinatraServer !!!"}
  }

  class Controller02 extends Controller {
    get("/test2") {request: Request => "xxxxx"}
  }
}
