package com.yen.urlShortener.controller

/** controller for app */

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

import com.yen.urlShortener.model.{hashCodeRequest, redisKeyRequest, urlRequest}
import com.yen.urlShortener.service.urlService

object Controller {

  val url_service = new urlService()

  // hashUrl
  class shortenUrl extends Controller {
    post("/api/v1/short"){
      requests:urlRequest =>
        val url = requests.url
        url_service.hashUrl(url)
    }
  }

  // list hash url
  class listAllUrl extends Controller {
    get("/api/v1/all_url"){
      requests:Request =>
      val r = url_service.listUrl()
      r
    }
  }

  // reverse hashcode to url
  class revereHashcode extends Controller {
    post("/api/v1/reverse"){
      requests:hashCodeRequest =>
        val code = requests.code
        val r = url_service.reverseHash(code)
        r
    }
  }

  // reverse hashcode to url
  //  class removeKey extends Controller {
  //    // TODO : fix this (use get for now)
  //    get("/api/v1/remove/:key"){
  //      requests:redisKeyRequest =>
  //        val key = requests.key
  //        url_service.deleteCache(key)
  //    }
  //  }

  class removeKey extends Controller {
    // TODO : fix this (use get for now)
    get("/api/v1/remove/:key"){
      requests:Request =>
        val key = requests.params("key")
        url_service.deleteCache(key)
    }
  }

  // test 1
  class postHelloWorld extends Controller {
    get("/hi") { request: Request =>
      "hello !!!"
    }
  }
}
