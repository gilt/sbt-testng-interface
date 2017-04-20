lazy val root = Project(id = "sbt-testng-interface", base = file("."))
  .settings(commonSettings: _*)
  .settings(
    version := "3.0.3",
    crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1"),
    libraryDependencies ++= Seq(
      "org.scala-sbt" % "test-interface" % "1.0" % "provided",
      "org.testng" % "testng" % "6.9.13.6" % "provided",
      "com.google.inject" % "guice" % "4.1.0" % "provided"))

lazy val testNGPlugin = Project(id = "sbt-testng-plugin", base = file("plugin"))
  .settings(commonSettings: _*)
  .settings(
    sbtPlugin := true,
    version := "3.0.3",
    crossScalaVersions := Seq("2.10.6"),
    scalacOptions += "-language:_")

lazy val commonSettings: Seq[Setting[_]] = gilt.GiltProject.jarSettings ++ Seq(
  organization := "com.gilt",
  scalaVersion := "2.10.6",
  scalacOptions ++= Seq("-unchecked", "-deprecation")) ++
  publishSettings

lazy val publishSettings: Seq[Setting[_]] = Seq(
  publishTo := Some("thirdparty-pom" at "https://nexus.gilt.com/nexus/content/repositories/thirdparty/"),
  publishMavenStyle := true,
  publishArtifact in Test := false
)
