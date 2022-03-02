package com.yen.heroAPI.server

import com.twitter.finagle.Http
import com.twitter.finagle.http.HttpMuxer
import com.twitter.server.TwitterServer
import com.twitter.util.Await

import com.yen.heroAPI.service.myService

object myServer extends TwitterServer {
  private val counter = statsReceiver.counter("requests_counter")

  def main() {
    val service = myService(counter, logger)
    HttpMuxer.addHandler("/echo", service)
    HttpMuxer.addHandler("/echo/", service)
    // And wait on the server
    //Await.ready(adminHttpServer)

    val server = Http.serve(":8888", service)
    onExit {
      server.close()
    }
    //Await.ready(server)
    Await.ready(adminHttpServer)
  }
}
