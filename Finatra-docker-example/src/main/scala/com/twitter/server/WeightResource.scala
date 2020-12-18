package com.twitter.server

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.inject.Logging
import org.joda.time.Instant

import scala.collection.mutable

class WeightResource extends Controller with Logging {

  val db = mutable.Map[String, List[Weight]]()

  post("/weights") { weight: Weight =>
    val r = time(s"Total time take to post weight for user '${weight.user}' is %d ms") {
      val weightsForUser = db.get(weight.user) match {
        case Some(weights) => weights :+ weight
        case None => List(weight)
      }
      db.put(weight.user, weightsForUser)
      response.created.location(s"/weights/${weight.user}")
    }
    r
  }

  get("/weights/:user") { request: Request =>
    info( s"""finding weight for user ${request.params("user")}""")
    db.getOrElse(request.params("user"), List())
  }

}

case class Weight(
                   user: String,
                   weight: Int,
                   status: Option[String],
                   postedAt: Instant = Instant.now()
                 )