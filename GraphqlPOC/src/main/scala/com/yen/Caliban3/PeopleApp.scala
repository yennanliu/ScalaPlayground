package com.yen.Caliban3

import sttp.client3._
import sttp.client3.asynchttpclient.zio.{AsyncHttpClientZioBackend, send}
import zio.{App, ExitCode, URIO, ZIO}

import com.yen.Caliban3.PeopleClient._

object PeopleApp extends App {

  case class People(`type`: String, lastName: String, firstName: String, id: String)

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] = {

//    val allPeople =
//      ( com.yen.Caliban3.PeopleClient.allPeople.`type` ~
//        com.yen.Caliban3.PeopleClient.allPeople.`lastName` ~
//        com.yen.Caliban3.PeopleClient.allPeople.`firstName` ~
//        com.yen.Caliban3.PeopleClient.allPeople.`id`
//        ).mapN(People)

    val query =
      Query.search2(Option("1000")) {
//        Searchable.person(
//          PeopleAttr.lastName ~
//            PeopleAttr.id
//        )
        Searchable.person {
          PeopleAttr.firstName ~
            PeopleAttr.lastName ~
            PeopleAttr.id
        }
      }

    val uri = uri"http://localhost:8080/graphql"

    println(">>> query = " + query.toString)

    send(query.toRequest(uri))
      .map(_.body)
      .absolve
      .tap(res => ZIO.debug(s"Result: $res"))
      .provideCustomLayer(AsyncHttpClientZioBackend.layer())
      .exitCode
  }
}
