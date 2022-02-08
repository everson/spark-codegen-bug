name := "spark-codegen-bug"

version := "0.0.1"

scalaVersion := "2.12.12"
crossScalaVersions := Seq("2.12.12", "2.13.7")

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.0" % "provided"
libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.13.2" % "test")

// test suite settings
Test / fork := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
// Show runtime of tests
Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
