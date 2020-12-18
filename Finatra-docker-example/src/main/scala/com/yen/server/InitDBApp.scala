package com.yen.server

// app init SQLite database
// https://github.com/twitter/finatra/blob/develop/http/src/test/scala/com/twitter/finatra/http/tests/integration/doeverything/main/controllers/DoEverythingController.scala

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

import sys.process._

// UDF
import services.SQLiteRunner.DBRunner

object InitDBApp extends DBServer

class DBServer extends HttpServer {
  override protected def defaultHttpsPort: String = ":8080"

  override protected def configureHttp(router: HttpRouter) {
    router.add[HelloController]
    router.add[DBController]
    router.add[DBDeleter]
  }
}

class HelloController extends Controller {
  get("/hello") {request: Request => "FitmanApp Hello world !"}
}

class DBController extends Controller {
  get("/db_init") { _: Request =>
    val db_runner = new DBRunner
    val setupFuture = db_runner.db.run(db_runner.setup)
    "init DB !"
  }
}

class DBDeleter extends Controller {
  get("/db_delete") { _: Request =>
    val deleteCommand = "rm db/coffees.db"
    val output = deleteCommand.!!
    "delete DB !"
  }
}
