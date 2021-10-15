package com.yen.urlShortener.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import com.yen.urlShortener.model.urlRequest
import com.yen.urlShortener.service.urlService

object SimpleController {

  val url_service = new urlService()

  // hashUrl
  class shortenUrl extends Controller {
    post("/api/v1/short"){
      requests:urlRequest =>
        val url = requests.url
        url_service.hashUrl(url)
    }
  }

  // test 1
  class postHelloWorld extends Controller {
    get("/hi") { request: Request =>
      "hello !!!"
    }
  }
}
