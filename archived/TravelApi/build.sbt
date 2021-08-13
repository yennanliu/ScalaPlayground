name := "TravelApi"

import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.yen"
ThisBuild / organizationName := "yen"

lazy val versions = new {
  val typesafe = "1.4.0"
  val finatra = "21.1.0"
  val logback = "1.1.7"
  val guice = "4.0"
}

// assemblyMergeStrategy in assembly := {
//  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//  case x => MergeStrategy.first
// }

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    name := "app",
    libraryDependencies += scalaTest % Test
  )

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
    // scala requests
    "com.lihaoyi" %% "requests" % "0.6.5",
    // ujson : https://www.lihaoyi.com/upickle/#uJson
    "com.lihaoyi" %% "ujson" % "0.9.5",

    // os libaray for Json IO : https://github.com/lihaoyi/os-lib#getting-started
    "com.lihaoyi" %% "os-lib" % "0.7.1",

    // finatra
    "com.twitter" %% "finatra-http" % versions.finatra,
    "ch.qos.logback" % "logback-classic" % versions.logback,
    "com.twitter" %% "finatra-http" % versions.finatra % "test",
    "com.twitter" %% "finatra-jackson" % versions.finatra % "test",
    "com.twitter" %% "inject-server" % versions.finatra % "test",
    "com.twitter" %% "inject-app" % versions.finatra % "test",
    "com.twitter" %% "inject-core" % versions.finatra % "test",
    "com.twitter" %% "inject-modules" % versions.finatra % "test",
    "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",
    "com.twitter" %% "finatra-http" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests"
)

conflictManager := ConflictManager.latestRevision
