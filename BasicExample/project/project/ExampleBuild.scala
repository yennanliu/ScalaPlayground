import sbt._

object ExampleBuild extends Build {
  lazy val root = Project("basic-example", file(".")) dependsOn docker
  lazy val docker = file("../..").getAbsoluteFile.toURI
}