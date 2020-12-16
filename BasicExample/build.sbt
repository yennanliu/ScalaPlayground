name := "basic-example"

organization := "sbtdocker"

version := "0.1-SNAPSHOT"

enablePlugins(DockerPlugin)

// Define a Dockerfile
dockerfile in docker := {
  val appPath = "/app"
  val jarFile = sbt.Keys.`package`.in(Compile, packageBin).value
  val classpath = (managedClasspath in Compile).value
  //val mainclass = mainClass.in(Compile, packageBin).value.getOrElse(sys.error("Expected exactly one main class"))
  val mainclass = "example.BasicExample"
  //val jarTarget = s"/app/${jarFile.getName}"
  val jarTarget = s"${appPath}/${jarFile.getName}"
  // Make a colon separated classpath with the JAR file
  //val classpathString = classpath.files.map("/app/" + _.getName).mkString(":") + ":" + jarTarget
  val classpathString = classpath.files.map(s"${appPath}/" + _.getName)
    .mkString(":") + ":" + jarTarget + ":."

  new Dockerfile {
    // Base image
    from("openjdk:8-jre")
    // Add all files on the classpath
    //add(classpath.files, "/app/")
    add(classpath.files, s"${appPath}/")
    // Add the JAR file
    add(jarFile, jarTarget)
    // On launch run Java with the classpath and the main class
    entryPoint("java", "-cp", classpathString, mainclass)
  }
}

// Set names for the image
// imageNames in docker := Seq(
//   ImageName("sbtdocker/basic:stable"),
//   ImageName(namespace = Some(organization.value),
//     repository = name.value,
//     tag = Some("v" + version.value))
// )


lazy val tag = taskKey[Unit]("Execute the docker tag shell script")
tag := {
   "docker tag default/basic-example default/basic-example"
}

// lazy val push = taskKey[Unit]("Execute the push to docker registry")
// push := {
//    "docker push efault/basic-example"!
// }
