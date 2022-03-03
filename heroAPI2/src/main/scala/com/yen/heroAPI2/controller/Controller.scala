package com.yen.heroAPI2.controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

object Controller {

  class hello extends Controller {
    get("/hi") { request: Request =>
      "hello !!!"
    }
  }
}
