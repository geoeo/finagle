name := "finagle"

version := "1.0"

// specs2 lib unmanaged

libraryDependencies ++= Seq("com.twitter" % "finagle-core_2.10" % "6.20.0",
  "com.twitter" % "finagle-http_2.10" % "6.20.0",
  "com.typesafe.play" % "play-json_2.10" % "2.4.0-M1",
  "org.scalamock" % "scalamock-scalatest-support_2.10" % "3.1.4",
  "junit" % "junit" % "4.11",
  "org.specs2" % "specs2_2.10" % "2.3.13")
