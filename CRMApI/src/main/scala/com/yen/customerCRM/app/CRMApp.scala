package com.yen.customerCRM.app

import com.twitter.finagle.Server
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

import com.yen.customerCRM.controller.AppController

object App extends CRMApp

class CRMApp extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[AppController.getUsers]
  }
}
