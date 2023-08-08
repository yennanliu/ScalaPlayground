name := "kinesis-producer"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  // log
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3",

  // akka
  "com.typesafe.akka" %% "akka-http" % "10.0.5",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5",

  // time
  "joda-time" % "joda-time" % "2.9.9",

  // spary-json
  "io.spray" %% "spray-json" % "1.3.3",

  // Amazon Kinesis Producer Library
  "com.amazonaws" % "amazon-kinesis-producer" % "0.12.8",

  // map to json
  "org.json4s" %% "json4s-native" % "3.5.3",

  "com.fasterxml.uuid" % "java-uuid-generator" % "3.1.5"
)

enablePlugins(PackPlugin)
