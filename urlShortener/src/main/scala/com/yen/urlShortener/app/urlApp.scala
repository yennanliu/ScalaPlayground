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
    router.add[Controller.listAllHashedUrl]
    router.add[Controller.reverseHashcode]
    router.add[Controller.removeKey]
    router.add[Controller.removeAllKey]
    router.add[Controller.redirect1]
    router.add[Controller.redirect2]
    router.add[Controller.postHelloWorld]
  }
}