package com.yen.TaxiService

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

import com.yen.TaxiService.controller.bookController

/**
 *  main application
 */

// entry point
object App extends serviceApp

class serviceApp extends HttpServer{

  //override val defaultHttpsPort: String = ":7777"
  override val defaultHttpsPort: String = ":8080"

  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[bookController.book]
    router.add[bookController.listAllCar]
    router.add[bookController.resetStatus]
    router.add[bookController.tickClock]
  }

}
