package com.yen.urlShortener.app

import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.HttpServer

import com.yen.urlShortener.controller.SimpleController

object App extends urlApp

class urlApp extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[SimpleController.shortenUrl]
    router.add[SimpleController.listAllUrl]
    router.add[SimpleController.revereHashcode]
    router.add[SimpleController.postHelloWorld]
  }
}
