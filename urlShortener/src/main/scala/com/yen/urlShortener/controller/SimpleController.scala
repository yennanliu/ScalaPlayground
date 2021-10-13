package com.yen.urlShortener.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

import com.yen.urlShortener.model.{HiRequest}

object SimpleController {

  class postHelloWorld extends Controller {
    post("/hi") { hiRequest: HiRequest =>
      "Hello " + hiRequest.name + " with id " + hiRequest.id
    }
  }
}
