package com.yen.playRESTApi1.model

// https://developer.lightbend.com/guides/play-rest-api/part-1/index.html
// https://github.com/yennanliu/play-samples/blob/2.8.x/play-scala-rest-api-example/app/v1/post/PostRepository.scala

class postModel {

}

class PostId private (val underlying: Int) extends AnyVal {
  override def toString: String = underlying.toString
}

object PostId {
  def apply(raw: String): PostId = {
    require(raw != null)
    new PostId(Integer.parseInt(raw))
  }
}

case class PostResource(
                         id: String,
                         link: String,
                         title: String,
                         body: String
                       )