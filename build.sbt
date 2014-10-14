name := "finagle"

version := "1.0"

libraryDependencies ++= Seq("com.twitter" % "finagle-core_2.10" % "6.20.0",
  "com.twitter" % "finagle-http_2.10" % "6.20.0",
  "com.typesafe.play" % "play-json_2.10" % "2.4.0-M1",
  "org.scalamock" %% "scalamock-specs2-support" % "3.0.1" % "test",
  "junit" % "junit" % "4.11",
  "org.specs2" % "specs2_2.10" % "2.3.13")
