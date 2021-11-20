name := "spark-app"

version := "1.0"

scalaVersion := "2.11.8"

val sparkVersion = "2.3.0"

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val versions = new {
  val typesafe = "1.4.0"
  val finatra = "21.1.0"
  val logback = "1.1.7"
  val guice = "4.0"
  val spark_core = "2.4.3"
  val scalactic = "3.1.0"
  val spark_sql = "2.4.3"
  val spark_csv = "1.4.0"
  val common_text = "1.8"
  val scalatest = "3.1.1"
}

libraryDependencies ++= Seq(
  // config
  "com.typesafe" % "config" % versions.typesafe,
  "org.apache.spark" %% "spark-core" % versions.spark_core,
  "org.scalactic" %% "scalactic" %  versions.scalactic,
  "org.apache.spark" %% "spark-sql" % versions.spark_sql,
  "com.databricks" %% "spark-csv" % versions.spark_csv,

  // others 
  "org.apache.commons" % "commons-text" % versions.common_text,

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

  // test
  "org.scalatest" %% "scalatest" % versions.scalatest % "test"
)

conflictManager := ConflictManager.latestRevision