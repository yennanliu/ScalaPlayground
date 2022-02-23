name := "heroAPI"

organization := "yen.com"

version := "1.0"

scalaVersion := "2.12.6"

lazy val versions = new {
  val typesafe = "1.4.0"
}

libraryDependencies ++= Seq(
  "com.twitter" %% "twitter-server" % "22.1.0"
)

conflictManager := ConflictManager.latestRevision
