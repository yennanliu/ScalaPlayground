//package com.yen.test
//
//// https://sangria-graphql.github.io/getting-started/#define-a-graphql-schema
//
//import sangria.macros.derive.{IncludeMethods, Interfaces}
//import sangria.schema._
//import sangria.macros.derive._
//import sangria.macros._
//import sangria.execution._
//import sangria.marshalling.circe._
//import io.circe.Json
//
//import scala.concurrent.Future
//
//import scala.concurrent.ExecutionContext.Implicits.global
//
//
//object Test5 extends App {
//
//  case class Picture(width: Int, height: Int, url: Option[String])
//
////  val PictureType = ObjectType(
////    "Picture",
////    "The product picture",
////
////    fields[Unit, Picture](
////      Field("width", IntType, resolve = _.value.width),
////      Field("height", IntType, resolve = _.value.height),
////      Field("url", OptionType(StringType),
////        description = Some("Picture CDN URL"),
////        resolve = _.value.url)))
//
//  implicit val PictureType =
//    deriveObjectType[Unit, Picture](
//      ObjectTypeDescription("The product picture"),
//      DocumentField("url", "Picture CDN URL"))
//
//  trait Identifiable {
//    def id: String
//  }
//
//
//  val IdentifiableType = InterfaceType(
//    "Identifiable",
//    "Entity that can be identified",
//
//    fields[Unit, Identifiable](
//      Field("id", StringType, resolve = _.value.id)))
//
//  case class Product(id: String, name: String, description: String) extends Identifiable {
//    def picture(size: Int): Picture =
//      Picture(width = size, height = size, url = Some(s"//cdn.com/$size/$id.jpg"))
//  }
//
//  val ProductType =
//    deriveObjectType[Unit, Product](
//      Interfaces(IdentifiableType),
//      IncludeMethods("picture"))
//
//
//  class ProductRepo {
//    private val Products = List(
//      Product("1", "Cheesecake", "Tasty"),
//      Product("2", "Health Potion", "+50 HP"))
//
//    def product(id: String): Option[Product] =
//      Products find (_.id == id)
//
//    def products: List[Product] = Products
//  }
//
//
//  val Id = Argument("id", StringType)
//
//  val QueryType = ObjectType("Query", fields[ProductRepo, Unit](
//    Field("product", OptionType(ProductType),
//      description = Some("Returns a product with specific `id`."),
//      arguments = Id :: Nil,
//      resolve = c => c.ctx.product(c arg Id)),
//
//    Field("products", ListType(ProductType),
//      description = Some("Returns a list of all available products."),
//      resolve = _.ctx.products)))
//
//
//  val schema = Schema(QueryType)
//
//
////  val query =
////    graphql"""
////    query MyProduct {
////      product(id: "2") {
////        name
////        description
////
////        picture(size: 500) {
////          width, height, url
////        }
////      }
////
////      products {
////        name
////      }
////    }
////  """
//
//  val query =
//    """
//      | query MyProduct {
//      |      product(id: "2") {
//      |        name
//      |        description
//      |
//      |        picture(size: 500) {
//      |          width, height, url
//      |        }
//      |      }
//      |
//      |      products {
//      |        name
//      |      }
//      |    }
//      |""".stripMargin
//
//
//  val result: Future[Json] =
//    Executor.execute(schema, query, new ProductRepo)
//
//  println(">>> result = " + result.foreach(println(_)))
//}