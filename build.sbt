name := "finagle"

version := "1.0"

scalaVersion := "2.11.2"


libraryDependencies ++= Seq("com.twitter" % "finagle-core_2.11" % "6.24.0",
  "com.twitter" %% "finagle-http" % "6.24.0",
  "com.typesafe.play" % "play-json_2.11" % "2.4.0-M2",
  "org.scalamock" %% "scalamock-specs2-support" % "3.2.1" % "test",
  "junit" % "junit" % "4.11")


