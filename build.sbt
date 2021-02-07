name := "stocker"

version := "0.1"

scalaVersion := "2.13.4"

idePackagePrefix := Some("com.stocker.api")

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client3" %% "core" % "3.1.0",
  "dev.zio" %% "zio" % "1.0.4-2"
)
