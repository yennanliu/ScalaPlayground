package com.yen.heroAPI.server

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.server.TwitterServer
import com.twitter.util.{Await, Future}


object myServer2 extends TwitterServer {

  val service = new Service[Request, Response] {
    def apply(request: Request) = {
      val response = Response(request.version, Status.Ok)
      response.contentString = "hello"
      Future.value(response)

    }
  }

  val service2 = new Service[Request, Response] {
    def apply(request: Request) = {
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