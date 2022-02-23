name := "HeroAPI"

import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.yen"
ThisBuild / organizationName := "yen"

lazy val versions = new {
  val twitterSererVersion = "22.1.0"
}

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    name := "app",
    libraryDependencies += scalaTest % Test
  )

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.twitter" %% "twitter-server" % versions.twitterSererVersion
)

conflictManager := ConflictManager.latestRevision