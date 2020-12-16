name := "akkahttp-docker-example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.11"

val akkaVersion = "2.4.16"
val akkaHttpVersion = "10.0.3"
val slf4jVersion = "1.7.25"
val sprayJsonVersion = "1.3.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "test",
  "io.spray" %% "spray-json" % sprayJsonVersion,
  "org.slf4j" % "slf4j-simple" % slf4jVersion
)

enablePlugins(DockerPlugin)
dockerfile in docker := {
  val appPath = "/app"
  val jarFile: File = sbt.Keys.`package`.in(Compile, packageBin).value
  val classpath = (managedClasspath in Compile).value
  val mainclass = "idv.jack.example.AkkaHTTPExample"
  val jarTarget = s"${appPath}/${jarFile.getName}"
  val classpathString = classpath.files.map(s"${appPath}/" + _.getName)
    .mkString(":") + ":" + jarTarget + ":."

  new Dockerfile {
    from("java")
    add(classpath.files, s"${appPath}/")
    add(jarFile, jarTarget)
    entryPoint("java", "-cp", classpathString, mainclass)
  }
}

lazy val tag = taskKey[Unit]("Execute the docker tag shell script")
tag := {
   "docker tag default/akkahttp-docker-example jackyohhub/akkahttp-docker-example"!
}

lazy val push = taskKey[Unit]("Execute the push to docker registry")
push := {
   "docker push jackyohhub/akkahttp-docker-example"!
}
