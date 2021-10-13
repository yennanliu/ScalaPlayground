name := "urlShortener"
organization := "yen.com"
version := "1.0"
scalaVersion := "2.11.8"

lazy val versions = new {
  val typesafe = "1.4.0"
  val finatra = "21.1.0"
  val logback = "1.1.7"
}

libraryDependencies ++= Seq(
  // config
  "com.typesafe" % "config" % "1.2.1", 
  // time
  "joda-time" % "joda-time" % "2.9.9",

  "org.slf4j" % "slf4j-nop" % "1.6.4",

  // scalatest
  "org.scalactic" %% "scalactic" % "3.2.0",
  "org.scalatest" %% "scalatest" % "3.2.0" % "test",
  "org.scalatest" %% "scalatest-funsuite" % "3.2.0" % "test",
  "org.scalatest" %% "scalatest-propspec" % "3.2.0" % "test",
  "org.scalatest" %% "scalatest-featurespec" % "3.2.0" % "test",

  // apache
  "org.apache.commons" % "commons-lang3" % "3.4",
  "org.apache.httpcomponents" % "httpclient" % "4.5.2",

  // Akka
  "com.typesafe.akka" %% "akka-actor" % "2.5.28",
  "com.typesafe.akka" %% "akka-remote" % "2.5.28",
  //"com.typesafe.akka" %% "akka-http" % "2.5.28"
  //"de.heikoseeberger" %% "akka-http-circe" % akkaHttpCirceVersion

  // finatra
  "com.twitter" %% "finatra-http" % versions.finatra,
  "ch.qos.logback" % "logback-classic" % versions.logback,
  "com.twitter" %% "finatra-http" % versions.finatra % "test",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter" %% "inject-server" % versions.finatra % "test",
  "com.twitter" %% "inject-app" % versions.finatra % "test",
  "com.twitter" %% "inject-core" % versions.finatra % "test",
  "com.twitter" %% "inject-modules" % versions.finatra % "test"
)

conflictManager := ConflictManager.latestRevision

//mainClass := Some("rdd.WordCount")

// assemblyMergeStrategy in assembly := {
//   case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//   case x => MergeStrategy.first
// }