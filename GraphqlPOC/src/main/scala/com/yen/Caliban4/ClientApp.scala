package com.yen.Caliban4

import sttp.client3._
import sttp.client3.asynchttpclient.zio.{AsyncHttpClientZioBackend, send}
import zio.{App, ExitCode, ZIO}

import com.yen.Caliban4.Client._

object ClientApp extends App {

  case class Train(`type`: String, platform: String, trainNumber: String, time: String, stops: List[String])

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] = {

    //    val allPeople =
    //      (Person.fullName ~
    //        Person.firstName ~
    //        Person.lastName ~
    //        Person.email).mapN(Person)

//    val query =
//      Query.search2(Option("1000")) {
//        Searchable.person {
//            Person.firstName ~
//            Person.lastName ~
//            Person.id
//        }
//      }

    val query =
      Query.get() {
        Searchable.friends {
          Person.firstName ~
            Person.lastName ~
            Person.id
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
