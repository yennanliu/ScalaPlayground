name := "GraphqlPOC"

organization := "com.yen"

version := "1.0"

scalaVersion := "2.12.0"

lazy val versions = new {
  val typesafe = "1.4.0"
  val logback = "1.1.7"
  val sangria = "2.0.0"
}

libraryDependencies ++= Seq(
  // config
  "com.typesafe" % "config" % "1.2.1",

  // time
  "joda-time" % "joda-time" % "2.9.9",

  // scalatest
  "org.scalatest" %% "scalatest" % "3.1.1" % "test",

  // sangria
  "org.sangria-graphql" %% "sangria" % versions.sangria
)

conflictManager := ConflictManager.latestRevision

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case "reference.conf" => MergeStrategy.concat
  case x => MergeStrategy.first
}