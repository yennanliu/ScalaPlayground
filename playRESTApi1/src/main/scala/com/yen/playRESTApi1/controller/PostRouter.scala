//package com.yen.playRESTApi1.controller
//
//// https://github.com/yennanliu/play-samples/blob/2.8.x/play-scala-rest-api-example/app/v1/post/PostController.scala
//import javax.inject.Inject
//import play.api.Logger
//import play.api.data.Form
//import play.api.libs.json.Json
//import play.api.mvc._
//import play.api.routing.SimpleRouter
//
//import scala.concurrent.{ExecutionContext, Future}
//
//class PostRouter @Inject()(controller: PostController) extends SimpleRouter
//
////class PostController @Inject()(cc: PostControllerComponents)(implicit ec: ExecutionContext)
////  extends PostBaseController(cc) {
////
////  def index: Action[AnyContent] = PostAction.async { implicit request =>
////    logger.trace("index: ")
////    postResourceHandler.find.map { posts =>
////      Ok(Json.toJson(posts))
////    }
////  }
////
////  def show(id: String): Action[AnyContent] = PostAction.async { implicit request =>
////    logger.trace(s"show: id = $id")
////    postResourceHandler.lookup(id).map { post =>
////      Ok(Json.toJson(post))
////    }
////  }
////}