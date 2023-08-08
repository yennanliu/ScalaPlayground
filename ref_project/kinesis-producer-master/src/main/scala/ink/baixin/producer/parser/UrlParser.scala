package ink.baixin.producer.parser

import akka.actor.ActorRef
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.directives.Credentials
import akka.http.scaladsl.server.{HttpApp, Route}
import ink.baixin.producer.logging.Logger
import ink.baixin.producer.utils.JsonUtil
import ink.baixin.producer.writer.KinesisProducerActor.{SendWithCallback, StopProducer}
import ink.baixin.producer.writer.ProducerEvent
import spray.json._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

class UrlParser(username: String, password: String)
               (implicit producerActor: ActorRef) extends HttpApp with Logger {

  // Required by the `ask` (?) method below
  implicit lazy val timeout = Timeout(5.seconds)

  def route: Route =
    path("w") {
      get {
        extract(ctx => ctx.request.uri.query()) { queryStringParams =>
          val data = JsonUtil.map2Json(queryStringParams.toMap)
          val JsObject(fields) = data.parseJson
          val JsString(aid) = fields("aid")
          val JsString(sid) = fields("sid")
          val partitionKey = aid + sid
          val producerEvent = ProducerEvent(partitionKey, data)
          producerActor ! SendWithCallback(producerEvent)
          complete(StatusCodes.OK)
        }
      }
    } ~ path("") {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Please specify the track path!"))
      }
    } ~ (path("security" / "stop")  & authenticateBasic(realm = "secure site", doorKeeper)) { _ =>
      get {
        val result = (producerActor ? StopProducer).mapTo[String]
        complete(result)
      }
    }

  def doorKeeper(credentials: Credentials): Option[String] =
    credentials match {
      case p@Credentials.Provided(id) if id == username && p.verify(password) => Some(id)
      case _ => None
    }

}
