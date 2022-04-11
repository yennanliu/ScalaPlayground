package com.yen.Caliban

import java.net.URL

import zio.{IO, UIO}

import scala.util.Try

// https://medium.com/@ghostdogpr/graphql-in-scala-with-caliban-part-1-8ceb6099c3c2

object Test4_1 extends App {


  trait PugService {
    def findPug(name: String): IO[PugNotFound, Pug]                          // GET request
    def randomPugPicture: UIO[String]                                        // GET request
    def addPug(pug: Pug): UIO[Unit]                                          // POST request
    def editPugPicture(name: String, pictureUrl: URL): IO[PugNotFound, Unit] // PUT request
  }

  sealed trait Color
  object Color {
    case object FAWN  extends Color
    case object BLACK extends Color
    case object OTHER extends Color
  }
  case class Pug(name: String, nicknames: List[String], pictureUrl: Option[URL], color: Color)
  case class PugNotFound(name: String) extends Throwable


  case class FindPugArgs(name: String)
  case class AddPugArgs(pug: Pug)
  case class EditPugPictureArgs(name: String, pictureUrl: URL)

  case class Queries(
                      findPug: FindPugArgs => IO[PugNotFound, Pug],
                      randomPugPicture: UIO[String])

  case class Mutations(
                        addPug: AddPugArgs => UIO[Unit],
                        editPugPicture: EditPugPictureArgs => IO[PugNotFound, Unit])

  val pugService: PugService = ???

  val queries = Queries(
    args => pugService.findPug(args.name),
    pugService.randomPugPicture)

  val mutations = Mutations(
    args => pugService.addPug(args.pug),
    args => pugService.editPugPicture(args.name, args.pictureUrl))

  import caliban.GraphQL.graphQL
  import caliban.RootResolver

  val api = graphQL(RootResolver(queries, mutations))

  import caliban.schema.{ ArgBuilder, Schema }
  import caliban.CalibanError.ExecutionError

  implicit val urlSchema: Schema[Any, URL] = Schema.stringSchema.contramap(_.toString)
  implicit val urlArgBuilder: ArgBuilder[URL] = ArgBuilder.string.flatMap(
    url => Try(new URL(url)).fold(_ => Left(ExecutionError(s"Invalid URL $url")), Right(_))
  )

  println(api.render)

  val query =
    """
      |{
      |  findPug(name: "Patrick") {
      |    name
      |    pictureUrl
      |  }
      |}
      |""".stripMargin

  for {
    interpreter <- api.interpreter
    result      <- interpreter.execute(query)
    _           <- zio.console.putStrLn(result.data.toString)
  } yield ()
}
