package com.yen.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}

object AppController {

  class hello extends Controller {
    get("/hello") {request: Request => "hello from Finatra Server !!!"}
  }
}
