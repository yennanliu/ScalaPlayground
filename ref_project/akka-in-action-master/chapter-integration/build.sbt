name := "integration"

version := "1.0"

organization := "manning"

libraryDependencies ++= {
  val akkaVersion = "2.4.19"
  val camelVersion    = "2.13.2"
  val activeMQVersion = "5.4.1"
  val akkaHttpVersion = "10.0.9"
  Seq(
    "org.scala-lang.modules"  %% "scala-xml" 				                 % "1.0.2",
    "com.typesafe.akka"       %% "akka-camel"                        % akkaVersion,
    "net.liftweb"             %  "lift-json_2.10"                    % "3.0-M1",
    "com.typesafe.akka"       %% "akka-actor"                        % akkaVersion,
    "com.typesafe.akka"       %% "akka-slf4j"                        % akkaVersion,
    "com.typesafe.akka"       %% "akka-http-core"                    % akkaHttpVersion,
    "com.typesafe.akka"       %% "akka-http"                         % akkaHttpVersion,
    "com.typesafe.akka"       %% "akka-http-spray-json"              % akkaHttpVersion,
    "com.typesafe.akka"       %% "akka-http-xml"                     % akkaHttpVersion,
    "ch.qos.logback"          %  "logback-classic"                   % "1.1.3",
    "commons-io"              %  "commons-io"                        % "2.0.1"         % "test",
    "com.typesafe.akka"       %% "akka-http-testkit"                 % "2.4.11"        % "test",
    "org.apache.camel"        %  "camel-mina"                        % camelVersion 	 % "test",
    "org.apache.activemq"     %  "activemq-camel"                    % activeMQVersion % "test",
    "org.apache.activemq"     %  "activemq-core"                     % activeMQVersion % "test",
    "org.apache.camel"        %  "camel-jetty"                       % camelVersion 	 % "test",
    "com.typesafe.akka"       %% "akka-testkit"                      % akkaVersion     % "test",
    "org.scalatest"           %% "scalatest"                         % "3.0.0"         % "test"
  )
}

