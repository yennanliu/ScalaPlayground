package com.yen.heroAPI.server

// https://twitter.github.io/twitter-server/

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.server.TwitterServer
import com.twitter.util.{Await, Future}

object BasicServer extends TwitterServer {
  
  val service = new Service[Request, Response] {
    def apply(request: Request) = {
      val response = Response(request.version, Status.Ok)
      response.contentString = "hello"
      Future.value(response)
    }
  }

  def main(): Unit = {
    val counter = statsReceiver.counter("requests_counter")
    val server = Http.serve(":8888", service)
    counter.incr()
    onExit {
      server.close()
    }
    Await.ready(server)
  }
}
