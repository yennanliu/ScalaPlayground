//package com.yen.Caliban3
//
//import sttp.client3._
//import sttp.client3.asynchttpclient.zio.{AsyncHttpClientZioBackend, send}
//import zio.{App, ExitCode, URIO, ZIO}
//
//import com.yen.Caliban3.PeopleClient._
//
//object PeopleApp extends App {
//
//  case class People(`type`: String, fullName: String, lastName: String, id: String)
//
//  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] = {
//
//    val People =
//      (People.`type` ~
//        People.fullName ~
//        People.lastName ~
//        People.id
//        ).mapN(People)
//
//    val query =
//      Query.search(Some("Berlin Ostbahnhof")) {
//        Searchable.stations {
//          Station.name ~
//            Station.hasWiFi ~
//            Station.timetable {
//              Timetable.nextDepatures {
//                trainInStation
//              } ~
//                Timetable.nextArrivals {
//                  trainInStation
//                }
//            }
//        }
//      }
//
//    val uri = uri"https://api.deutschebahn.com/free1bahnql/v1/graphql"
//
//    send(query.toRequest(uri))
//      .map(_.body)
//      .absolve
//      .tap(res => ZIO.debug(s"Result: $res"))
//      .provideCustomLayer(AsyncHttpClientZioBackend.layer())
//      .exitCode
//  }
//}
