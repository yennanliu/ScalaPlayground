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

  // list url
  class listAllUrl extends Controller {
    get("/api/v1/all_url"){
      requests:Request =>
      val r = url_service.listUrl()
      r
    }
  }

  // list hashed url
  class listAllHashedUrl extends Controller {
    get("/api/v1/all_hashed"){
      requests:Request =>
        val r = url_service.listValue()
        r
    }
  }

  // reverse hashcode to url
  class reverseHashcode extends Controller {
    get("/api/v1/reverse/:key"){
      requests:Request =>
        val key = requests.params("key")
        val r = url_service.reverseHash(key)
        r.getOrElse("hashcode not exists")
    }
  }

  class removeKey extends Controller {
    get("/api/v1/remove/:key"){
      requests:Request =>
        val key = requests.params("key")
        url_service.deleteCache(key)
    }
  }

  class removeAllKey extends Controller {
    get("/api/v1/removeAll"){
      requests:Request =>
        url_service.deleteAllCache()
    }
  }

  // test 1
  class postHelloWorld extends Controller {
    get("/hi") { request: Request =>
      "hello !!!"
    }
  }
}
