import com.typesafe.sbt.packager.docker._

name := "Finatra-docker-example"
version := "1.0"
scalaVersion := "2.11.8"

lazy val versions = new {
  val typesafe = "1.4.0"
  val finatra = "20.8.0"
  val logback = "1.1.7"
  val guice = "4.0"
}

assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  // scala
  "org.scala-lang" % "scala-library" % "2.13.0",

  // config
  "com.typesafe" % "config" % versions.typesafe,

  // finatra
  "com.twitter" %% "finatra-http" % versions.finatra,
  "ch.qos.logback" % "logback-classic" % versions.logback,

  "com.twitter" %% "finatra-http" % versions.finatra % "test",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter" %% "inject-server" % versions.finatra % "test",
  "com.twitter" %% "inject-app" % versions.finatra % "test",
  "com.twitter" %% "inject-core" % versions.finatra % "test",
  "com.twitter" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "com.twitter" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests",

  // testing
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.specs2" %% "specs2-mock" % "2.4.17" % "test",
  "com.twitter" %% "finatra-http" % "20.8.0" % "test" classifier "tests",
  "org.scalactic" %% "scalactic" % "3.2.0",
  "org.scalatest" %% "scalatest" % "3.2.0" % "test",
  "org.scalatest" %% "scalatest-funsuite" % "3.2.0" % "test",
  "org.scalatest" %% "scalatest-propspec" % "3.2.0" % "test",
  "org.scalatest" %% "scalatest-featurespec" % "3.2.0" % "test"
)

//enablePlugins(DockerPlugin)

// dockerfile in docker := {
//   val appPath = "/app"
//   val jarFile: File = sbt.Keys.`package`.in(Compile, packageBin).value
//   val classpath = (managedClasspath in Compile).value
//   val mainclass = "com.twitter.server.FinatraApp"
//   val jarTarget = s"${appPath}/${jarFile.getName}"
//   val classpathString = classpath.files.map(s"${appPath}/" + _.getName)
//     .mkString(":") + ":" + jarTarget + ":."

//   new Dockerfile {
//     from("java")
//     add(classpath.files, s"${appPath}/")
//     add(jarFile, jarTarget)
//     entryPoint("java", "-cp", classpathString, mainclass)
//   }
// }

 //dockerExposedPorts ++= Seq(9990, 8888)
 dockerCommands := Seq(
   Cmd("FROM", "adoptopenjdk/openjdk8:centos"),
   //Cmd("EXPOSE", dockerExposedPorts.value.map(_.toString):_*), //dockerExposedPorts.map(_.toString)), //dockerExposedPorts.value.map(_.toString):_*),
   Cmd("COPY", "1/opt", "/opt"),
   Cmd("COPY", "2/opt", "/opt"),
   Cmd("RUN", "chmod", "-R", "u=rX,g=rX", "/opt/docker"),
   Cmd("RUN", "chmod", "u+x,g+x", "/opt/docker/bin/Finatra-docker-example"),
   Cmd("ADD", "init.sh", "/opt/docker/bin/"),
   Cmd("RUN", "chmod", "u+x,g+x", "/opt/docker/bin/init.sh"),
   Cmd("ENTRYPOINT", "/opt/docker/bin/init.sh")
 )

//lazy val tag = taskKey[Unit]("Execute the docker tag shell script")
//tag := {
//  "docker tag default/finatra-docker-example yen/finatra-docker-example"
//}
//
//lazy val push = taskKey[Unit]("Execute the push to docker registry")
//push := {
//  "docker push yen/finatra-docker-example"
//}

conflictManager := ConflictManager.latestRevision