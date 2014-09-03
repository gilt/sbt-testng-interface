import sbt._
import sbt.Keys._

object TestNGPluginBuild extends Build {
  lazy val root = Project(
    id = "sbt-testng-interface",
    base = file("."),
    settings = Project.defaultSettings ++ commonSettings ++ Seq(
      version := "3.0.2",
      crossScalaVersions := Seq("2.9.1", "2.9.2", "2.9.3", "2.10.4", "2.11.2"),
      libraryDependencies ++= Seq(
        "org.scala-sbt" % "test-interface" % "1.0" % "provided",
        "org.testng" % "testng" % "6.8.8" % "provided",
        "com.google.inject" % "guice" % "2.0" % "provided")))

  lazy val testNGPlugin = Project(
    id = "sbt-testng-plugin",
    base = file("plugin"),
    settings = Project.defaultSettings ++ commonSettings ++ Seq(
      sbtPlugin := true,
      version := "3.0.2",
      crossScalaVersions := Seq("2.10.4"),
      scalacOptions += "-language:_"))

  lazy val commonSettings: Seq[Setting[_]] = gilt.GiltProject.jarSettings ++ Seq(
    organization := "com.gilt",
    scalaVersion := "2.10.4",
    scalacOptions ++= Seq("-unchecked", "-deprecation")) ++ 
    publishSettings

  lazy val publishSettings: Seq[Setting[_]] = Seq(
    publishTo := Some("thirdparty-pom" at "https://nexus.gilt.com/nexus/content/repositories/thirdparty/"),
    publishMavenStyle := true,
    publishArtifact in Test := false)
}
