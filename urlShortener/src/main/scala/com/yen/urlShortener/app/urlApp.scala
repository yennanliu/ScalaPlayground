package com.yen.urlShortener.app

/** url shorten app */

import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.HttpServer

import com.yen.urlShortener.controller.Controller

// entry point
object App extends urlApp

class urlApp extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[Controller.shortenUrl]
    router.add[Controller.listAllUrl]
    router.add[Controller.revereHashcode]
    router.add[Controller.postHelloWorld]
  }
}