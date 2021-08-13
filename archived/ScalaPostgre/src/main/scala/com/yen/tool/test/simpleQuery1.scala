//package com.yen.tool.test
//
//// https://tpolecat.github.io/skunk/tutorial/Query.html#single-column-query
//
//import cats.effect._
//import skunk._
//import skunk.implicits._
//import skunk.codec.all._
//import java.time.OffsetDateTime
//import natchez.Trace.Implicits.noop
//
//// data model
//case class Country(name:String, code:String, population:Int)
//
//// service interface
//trait Service[F[_]] {
//  def currentTimestamp: F[OffsetDateTime]
//  def countriesByName(pat: String): Stream[F, Country]
//}
//
//// companion object with constructor
//object Service {
//
//  private val timestamp: Query[Void, OffsetDateTime] =
//    sql"select current_timestamp".query(timestamptz)
//
//  private val countries: Query[String, Country] =
//    sql"""
//      SELECT name, code, population
//      FROM   country
//      WHERE  name like $text
//    """.query(varchar ~ bpchar(3) ~ int4)
//      .gmap[Country]
//
//  def fromSession[F[_]: Applicative](s: Session[F]): Resource[F, Service[F]] =
//    s.prepare(countries).map { pq =>
//
//      // Our service implementation. Note that we are preparing the query on construction, so
//      // our service can run it many times without paying the planning cost again.
//      new Service[F] {
//        def currentTimestamp: F[OffsetDateTime] = s.unique(timestamp)
//        def countriesByName(pat: String): Stream[F,Country] = pq.stream(pat, 32)
//      }
//
//    }
//}
//
//// main app
//object simpleQuery1 extends IOApp { // entry point
//
//  // session
//  val session:Resource[IO, Session[IO]] =
//    Session.single(
//      // TODO : update below config when set up DB
//      host = "localhost",
//      user = "admin",
//      database = "test_db",
//      password =  Some("password")
//    )
//
//  // implement run method
//  // our entry point ... there is no indication that we're using a database at all!
//  def run(args: List[String]): IO[ExitCode] =
//    service.use { s =>
//      for {
//        ts <- s.currentTimestamp
//        _  <- IO(println(s"timestamp is $ts"))
//        _  <- s.countriesByName("U%")
//          .evalMap(c => IO(println(c)))
//          .compile
//          .drain
//      } yield ExitCode.Success
//    }
//}
