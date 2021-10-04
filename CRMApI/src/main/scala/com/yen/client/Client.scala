package com.yen.client

// https://twitter.github.io/finagle/guide/Quickstart.html

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}

object Client extends App {

  // TODO : fix below clientURL, request.host  (for local host)
  //val clientURL = "www.scala-lang.org:80"
  val clientURL = "localhost:8888"

  val client:Service[http.Request, http.Response] = Http.newService(clientURL)
  val request = http.Request(http.Method.Get, "/")

  //request.host = "www.scala-lang.org"
  request.host = "localhost:8888/api/v1"

  val response:Future[http.Response] = client(request)

  Await.result(response.onSuccess{ rep:http.Response => println("GET success " + rep) })
}
