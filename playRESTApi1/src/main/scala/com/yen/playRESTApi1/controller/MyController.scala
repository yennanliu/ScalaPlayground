package com.yen.playRESTApi1.controller

// https://developer.lightbend.com/guides/play-rest-api/part-1/index.html

import javax.inject.Inject
import play.api.mvc._

import scala.concurrent._

class MyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index1: Action[AnyContent] = Action { implicit request =>
    val r: Result = Ok("hello world")
    r
  }

  def asyncIndex: Action[AnyContent] = Action.async { implicit request =>
    val r: Future[Result] = Future.successful(Ok("hello world"))
    r
  }
}