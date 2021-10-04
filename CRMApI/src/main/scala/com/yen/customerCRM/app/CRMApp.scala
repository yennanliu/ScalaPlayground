package com.yen.customerCRM.app

import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.HttpServer

import com.yen.customerCRM.controller.AppController

object App extends CRMApp

class CRMApp extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[AppController.getUsers]
    router.add[AppController.getUser]
    router.add[AppController.modifyUser]
    router.add[AppController.deleteUser]
    router.add[AppController.postHelloWorld]
  }
}
