package com.yen.heroAPI.service

import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.stats.Counter
import com.twitter.util.{Future, Time}

class heroService(requestCounter: Counter)
  extends com.twitter.finagle.Service[Request, Response] {

  override def apply(request: Request): Future[Response] = {
    requestCounter.incr()
    val response = Response(request.version, Status.Ok)
    response.contentString = s"Hello at ${Time.now.format("yyyy-MM-DD hh:mm:ss.sss")}"
    Future.value(response)
  }

  def _print():Unit={
    println(">>>> heroService")
  }

}

object heroService{
  def apply(requestCounter: Counter):heroService ={
    new heroService(requestCounter: Counter)
  }
}