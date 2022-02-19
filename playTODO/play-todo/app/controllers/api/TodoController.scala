package controllers.api

// https://ixorasolution.com/blog/how-to-build-rest-api-with-scala-play-framework-1

import javax.inject._
import models.Todo
import play.api.libs.json._
import play.api.mvc._

class TodoController @Inject()(
                                cc: ControllerComponents
                              ) extends AbstractController(cc) {

  implicit val todoFormat = Json.format[Todo]

  def getAll = Action {
    val todo = new Todo(1, "item 1", false)
    Ok(Json.toJson(todo))
  }
}
