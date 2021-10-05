package controllers

// https://github.com/playframework/play-samples/blob/2.8.x/play-scala-starter-example/app/controllers/CountController.scala

import javax.inject._

import play.api.mvc._
import services.Counter

@Singleton
class CountController @Inject() (cc: ControllerComponents,
                                 counter: Counter) extends AbstractController(cc) {

  /**
   * Create an action that responds with the [[Counter]]'s current
   * count. The result is plain text. This `Action` is mapped to
   * `GET /count` requests by an entry in the `routes` config file.
   */
  def count = Action { Ok(counter.nextCount().toString) }

}
