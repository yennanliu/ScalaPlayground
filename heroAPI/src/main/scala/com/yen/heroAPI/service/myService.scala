package com.yen.heroAPI.service

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.stats.Counter
import com.twitter.util.logging.Logger
import com.twitter.util.{Future, Time}

class myService(requestCounter: Counter, logger: Logger)
  extends Service[Request, Response] {

  override def apply(request: Request): Future[Response] = {
    logger.info("Received a request at " + Time.now)
    requestCounter.incr()
    val response = Response(request.version, Status.Ok)
    response.contentString = s"Hello at ${Time.now.format("yyyy-MM-DD hh:mm:ss.sss")}"
    Future.value(response)
  }
}

object myService {

  def apply(requestCounter: Counter, logger: Logger): myService =
    new myService(requestCounter, logger)
}
