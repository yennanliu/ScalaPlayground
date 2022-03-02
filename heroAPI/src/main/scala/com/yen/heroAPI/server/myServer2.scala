package com.yen.heroAPI.server

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{HttpMuxer, Request, Response, Route, RouteIndex, Status}
import com.twitter.server.TwitterServer
import com.twitter.server.handler.{AbortHandler, ReplyHandler}
import com.twitter.util.{Await, Future}
import com.twitter.finagle.http.Method.Post

import com.yen.heroAPI.service.heroService

object myServer2 extends TwitterServer {

  val service = new Service[Request, Response] {
    def apply(request: Request) = {
      val response = Response(request.version, Status.Ok)
      response.contentString = "hello"
      Future.value(response)

    }
  }

  // https://github.com/twitter/twitter-server/blob/develop/server/src/main/scala/com/twitter/server/Lifecycle.scala
  val service2 = new Service[Request, Response] {
    def apply(request: Request) = {
      // add this to end point (so http://localhost:8888/test)
      HttpMuxer.addHandler(
        Route(
          pattern = "/test/",
          service
        )
      )

//      HttpMuxer.addHandler(
//        Route(
//          pattern = "/test2/",
//          heroService.apply()
//        )
//      )

      HttpMuxer.addHandler(
        Route(
          pattern = "/health_test",
          handler = new ReplyHandler("OK\n"),
          index = Some(RouteIndex(alias = "Health", group = group))
        )
      )

      val response = Response(request.version, Status.Ok)
      val msg = "hello0000000"
      println(">>>>>>>>" + msg)
      response.contentString = msg
      Future.value(response)
    }
  }

  def main(): Unit = {
    val server = Http.serve(":8888", service2)
    onExit {
      server.close()
    }
    Await.ready(server)
  }
}