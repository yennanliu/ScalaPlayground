package com.twitter.server

// https://twitter.github.io/finatra/user-guide/http/server.html

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.{Await, Future}

object ExampleServerMain extends ExampleServer

class ExampleServer extends HttpServer {

    val service = new Service[Request, Response] {
        def apply(request: Request) = {
          val response = Response(request.version, Status.Ok)
          response.contentString = "hello"
          Future.value(response)
        }
  }

  override def configureHttp(router: HttpRouter): Unit = {
    val server = Http.serve(":8888", service)
    onExit {
      server.close()
    }
    Await.ready(server)
  }
}