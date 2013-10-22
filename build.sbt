name := "gitlehunt"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "postgresql" % "postgresql" % "8.4-702.jdbc4",
  "org.webjars" % "webjars-play_2.10" % "2.2.0",
  "org.webjars" % "angularjs" % "1.1.5-1",
  "org.webjars" % "bootstrap" % "3.0.0"
)     

play.Project.playJavaSettings
