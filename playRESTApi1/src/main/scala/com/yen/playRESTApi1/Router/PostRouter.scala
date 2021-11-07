//package com.yen.playRESTApi1.Router
//
//import com.google.inject.Inject
//import play.api.routing.SimpleRouter
//
//// https://developer.lightbend.com/guides/play-rest-api/part-1/index.html
//
//class PostRouter @Inject()(controller: PostController) extends SimpleRouter
//
////import javax.inject.Inject
////import play.api.routing.Router.Routes
////import play.api.routing.SimpleRouter
////import play.api.routing.sird._
////import com.yen.playRESTApi1.controller.PostController
////import com.yen.playRESTApi1.model.PostId
////
////class PostRouter @Inject()(controller: PostController) extends SimpleRouter {
////  val prefix = "/v1/posts"
////
////  def link(id: PostId): String = {
////    import com.netaporter.uri.dsl._
////    val url = prefix / id.toString
////    url.toString()
////  }
////
////  override def routes: Routes = {
////    case GET(p"/") =>
////      controller.index
////
////    case POST(p"/") =>
////      controller.process
////
////    case GET(p"/$id") =>
////      controller.show(id)
////  }
////
////}