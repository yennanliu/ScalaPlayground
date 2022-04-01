name := "TaxiService"

organization := "com.yen"

version := "1.0"

scalaVersion := "2.12.0"

lazy val versions = new {
  val typesafe = "1.4.0"
  //val finatra = "21.1.0"
  val finatra = "22.2.0"
  val logback = "1.1.7"
}

libraryDependencies ++= Seq(
  // config
  "com.typesafe" % "config" % "1.2.1",

  // time
  "joda-time" % "joda-time" % "2.9.9",

  // scalatest
  "org.scalatest" %% "scalatest" % "3.1.1" % "test",

  // finatra
  //"com.twitter" %% "finatra-http" % versions.finatra,
  "com.twitter" %% "finatra-http-server" % versions.finatra,
  //"com.twitter" %% "finatra-http" % versions.finatra % "test",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter" %% "inject-server" % versions.finatra,
  "com.twitter" %% "finatra-http-server" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-app" % versions.finatra % "test",
  "com.twitter" %% "inject-core" % versions.finatra % "test",
  "com.twitter" %% "inject-modules" % versions.finatra % "test"
)

conflictManager := ConflictManager.latestRevision

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case "reference.conf" => MergeStrategy.concat
  case x => MergeStrategy.first
}