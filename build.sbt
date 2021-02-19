name := "stocker"

version := "0.1"

scalaVersion := "2.13.4"

idePackagePrefix := Some("com.stocker.api")

val sttpVersion = "2.2.4"
libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client" %% "core" % sttpVersion,
  "com.softwaremill.sttp.client" %% "akka-http-backend" % sttpVersion,
  "com.softwaremill.sttp.client" %% "circe" % sttpVersion,
  "com.softwaremill.sttp.client" %% "slf4j-backend" % sttpVersion,
  "com.softwaremill.sttp.client" %% "async-http-client-backend-zio" % sttpVersion
)

libraryDependencies ++= Seq(
"dev.zio" %% "zio" % "1.0.4-2"
)

val circeVersion = "0.12.3"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
