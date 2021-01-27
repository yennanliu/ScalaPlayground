name := "ScalaScraper"

import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.yen"
ThisBuild / organizationName := "yen"

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

libraryDependencies ++= Seq(

    // scala requests
    "com.lihaoyi" %% "requests" % "0.6.5",
    // ujson : https://www.lihaoyi.com/upickle/
    "com.lihaoyi" %% "upickle" % "0.9.5"
)

conflictManager := ConflictManager.latestRevision
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
