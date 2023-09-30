ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-zio-http"
  )

libraryDependencies += "dev.zio"             %% "zio"       % "2.0.16"
libraryDependencies += "dev.zio"             %% "zio-http"  % "3.0.0-RC2"
libraryDependencies += "com.google.code.gson" % "gson"      % "2.8.8"
libraryDependencies += "org.yaml"             % "snakeyaml" % "1.27"
