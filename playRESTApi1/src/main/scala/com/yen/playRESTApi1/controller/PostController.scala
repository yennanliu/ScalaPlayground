//package com.yen.playRESTApi1.controller
//
//// https://developer.lightbend.com/guides/play-rest-api/part-1/index.html
//
//import javax.inject.Inject
//
//import play.api.Logger
//import play.api.data.Form
//import play.api.libs.json.Json
//import play.api.mvc._
//
//import scala.concurrent.{ ExecutionContext, Future }
//
//class PostController @Inject()(cc: PostControllerComponents)(implicit ec: ExecutionContext)
//  extends PostBaseController(cc) {
//
//  def index: Action[AnyContent] = PostAction.async { implicit request =>
//    logger.trace("index: ")
//    postResourceHandler.find.map { posts =>
//      Ok(Json.toJson(posts))
//    }
//  }
//
//  def show(id: String): Action[AnyContent] = PostAction.async { implicit request =>
//    logger.trace(s"show: id = $id")
//    postResourceHandler.lookup(id).map { post =>
//      Ok(Json.toJson(post))
//    }
//  }
//}