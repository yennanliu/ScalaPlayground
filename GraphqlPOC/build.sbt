name := "GraphqlPOC"

organization := "com.yen"

version := "1.0"

scalaVersion := "2.12.13"

lazy val versions = new {
  val typesafe = "1.4.0"
  val logback = "1.1.7"
  val sangria = "2.0.0"
}

val calibanVersion = "1.4.0"

enablePlugins(CalibanPlugin)

libraryDependencies ++= Seq(

  // config
  "com.typesafe" % "config" % "1.2.1",

  // time
  "joda-time" % "joda-time" % "2.9.9",

  // scalatest
  "org.scalatest" %% "scalatest" % "3.1.1" % "test",

  // sangria
  "org.sangria-graphql" %% "sangria" % versions.sangria,
  "org.sangria-graphql" %% "sangria-spray-json" % "1.0.2",
  "org.sangria-graphql" %% "sangria-circe" % "1.3.2",

  // caliban
  "com.github.ghostdogpr" %% "caliban-client" % "1.4.0",
  "com.github.ghostdogpr" %% "caliban-zio-http" % "1.4.0",
  "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % "3.3.18",

  // graphql-java
  "com.graphql-java" % "graphql-java" % "15.0",

  // scala request
  "com.lihaoyi" %% "requests" % "0.1.8",

  // sttp
  "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % "3.3.15"
)

conflictManager := ConflictManager.latestRevision

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case "reference.conf" => MergeStrategy.concat
  case x => MergeStrategy.first
}