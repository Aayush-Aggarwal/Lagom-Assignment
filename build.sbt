organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `hello-lagom-helloworld` = (project in file("."))
  .aggregate(`hello-lagom-helloworld-api`,`hello-lagom-helloworld-impl`, `hello-lagom-helloworld-stream-api`,
    `hello-lagom-helloworld-stream-impl`, `hello-lagom-helloworld-consumer-api`,
    `user-lagom-api`,`user-lagom-impl`)

lazy val `hello-lagom-helloworld-api` = (project in file("hello-lagom-helloworld-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hello-lagom-helloworld-impl` = (project in file("hello-lagom-helloworld-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`hello-lagom-helloworld-api`)

lazy val `hello-lagom-helloworld-consumer-api` = (project in file("hello-lagom-helloworld-consumer-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )
lazy val `user-lagom-api` = (project in file("user-lagom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )
lazy val `user-lagom-impl` = (project in file("user-lagom-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`user-lagom-api`)


lazy val `hello-lagom-helloworld-stream-api` = (project in file("hello-lagom-helloworld-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hello-lagom-helloworld-stream-impl` = (project in file("hello-lagom-helloworld-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`hello-lagom-helloworld-stream-api`, `hello-lagom-helloworld-api`)

lagomUnmanagedServices in ThisBuild := Map("external-service" -> "https://jsonplaceholder.typicode.com:443")

lagomKafkaEnabled in ThisBuild := false
lagomKafkaAddress in ThisBuild := "localhost:9092"
