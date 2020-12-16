import sbt._

object Build extends Build {
  lazy val root = Project("basic-example", file(".")) dependsOn docker
  lazy val docker = file("/..").getAbsoluteFile.toURI
}