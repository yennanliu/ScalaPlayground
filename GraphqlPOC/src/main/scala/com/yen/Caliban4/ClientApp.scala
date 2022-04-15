package com.yen.Caliban4

import caliban.client.Operations.RootQuery
import caliban.client.SelectionBuilder
import sttp.client3._
import sttp.client3.asynchttpclient.zio.{AsyncHttpClientZioBackend, send}
import zio.{App, ExitCode, ZIO}
import com.yen.Caliban4.Client._

object ClientApp extends App {

  //case class person(`type`: String, id: String, firstName: String, lastName: String, email: List[String])

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] = {

//        val allPeople =
//          ( Person.fullName ~
//            Person.firstName ~
//            Person.lastName ~
//            Person.email).mapN(person)

//    val query =
//      Query.allPeople {
//        person.id ~
//        person.lastName
//      }

    val query =
      Query.allPeople {
        person.id ~
        person.lastName ~
        person.firstName ~
        person.friends(
          person.id ~
            person.lastName ~
            person.firstName
        )
      }

    val uri = uri"http://localhost:8080/graphql"

    println(">>> query = " + query.toGraphQL().toString)

    send(query.toRequest(uri))
      .map(_.body)
      .absolve
      .tap(res => ZIO.debug(s"Result: $res"))
      .provideCustomLayer(AsyncHttpClientZioBackend.layer())
      .exitCode
  }
}
