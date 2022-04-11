package com.yen.dev

// https://sangria-graphql.github.io/getting-started/#getting-started-with-sangria

import sangria.schema._
import sangria.macros.derive._

import sangria.execution._

object Test1 extends App {

  case class Picture(width: Int, height: Int, url: Option[String])

//  val PictureType = ObjectType(
//    "Picture",
//    "The product picture",
//
//    fields[Unit, Picture](
//      Field("width", IntType, resolve = _.value.width),
//      Field("height", IntType, resolve = _.value.height),
//      Field("url", OptionType(StringType),
//        description = Some("Picture CDN URL"),
//        resolve = _.value.url)))

  implicit val PictureType =
    deriveObjectType[Unit, Picture](
      ObjectTypeDescription("The product picture"),
      DocumentField("url", "Picture CDN URL"))


//  val query =
//    graphql"""
//    query MyProduct {
//      product(id: "2") {
//        name
//        description
//
//        picture(size: 500) {
//          width, height, url
//        }
//      }
//
//      products {
//        name
//      }
//    }
//  """
}
