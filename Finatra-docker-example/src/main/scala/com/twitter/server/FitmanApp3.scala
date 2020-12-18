package com.twitter.server

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object FitmanApp3 extends FitmanServer3

class FitmanServer3 extends HttpServer{
    override protected def defaultHttpsPort: String = ":8080"
    override protected def defaultHttpServerName: String = "FitMan"

    override protected def configureHttp(router: HttpRouter) {
      router
        //.filter[CommonFilters]
        .add[HelloController]
        .add[WeightResource]
    }

  class CommonFilters{
    def check(input:String):String = input match{
      case input if isEmpty(input) => input
      case _ => null
    }
    def isEmpty(x: String) = x == null || x.trim.isEmpty
  }

}
