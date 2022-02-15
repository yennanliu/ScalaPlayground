package com.yen.urlShortener.controller

/** controller for app */

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

import com.yen.urlShortener.model.{hashCodeRequest, redisKeyRequest, urlRequest}
import com.yen.urlShortener.service.urlService
import com.yen.urlShortener.common.Common

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

  // redirect
  // https://gitter.im/twitter/finatra?at=598b74d5329651f46e05d13e
  class redirect1 extends Controller {
    println("redirect !!!!")
    get("/api/v1/redirect/:key") { requests: Request =>
      val key = requests.params("key")
      val r = url_service.reverseHash(key) match {
        // TODO : fix below
        case r => {
          println("redirect now")
          println(">>> r = " + r)
          val reversed_url = Common.show(r)
          println(">>> reversed_url = " + reversed_url)
          response.temporaryRedirect.location("www.python.org")
        }
        case _ => {
          println("hashcode not exists")
          response.temporaryRedirect.location("/hi")
        }
      }
    }
  }

  class redirect2 extends Controller {
    println("redirect !!!!")
    get("/api/v1/redirect") { request: Request =>
      response.temporaryRedirect.location("/hi")
    }
  }
  // test 1
  class postHelloWorld extends Controller {
    get("/hi") { request: Request =>
      "hello !!!"
    }
  }
}
