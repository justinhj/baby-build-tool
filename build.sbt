import sbt._

lazy val babybuildtool = (project in file(".")).
    settings (
      name := "babybuildtool",
      organization := "com.justinhj",
      version := "0.2-SNAPSHOT",
      scalaVersion := "2.12.6"
      // add other settings here
    )

/* scala versions and options */
scalaVersion := "2.12.6"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  , "-unchecked"
  , "-encoding", "UTF-8"
  , "-Xlint"
  , "-Xverify"
  , "-feature"
  ,"-Ypartial-unification"
  ,"-Xfatal-warnings"
  , "-language:postfixOps"
  //,"-optimise"
)

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.7", "-target", "1.7")

// javaOptions in Universal ++= Seq(
//   "-J-server",
//   "-J-Xms1g -Xmx4g",
//   "-J-XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled",
//   "-J-XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=68",
//   "-J-XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark",
//   "-J-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"
// )

val CirceVersion = "0.9.3"
val MonixVersion = "3.0.0-M3"
val ScalaZVersion = "7.2.23"
val ShapelessVersion = "2.3.3"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  // -- testing --
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  // -- Logging --
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  // -- json/circe --
  "io.circe" %% "circe-core" % CirceVersion,
  "io.circe" %% "circe-generic" % CirceVersion,
  "io.circe" %% "circe-parser" % CirceVersion,
  "io.circe" %% "circe-jawn" % CirceVersion,
  "io.circe" %% "circe-yaml" % "0.8.0",
  // monix
  "io.monix" %% "monix" % MonixVersion,
  // shapeless
  "com.chuusai" %% "shapeless" % ShapelessVersion,
  // scalaz
  "org.scalaz" %% "scalaz-core" % ScalaZVersion,
  // test
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  // li haoyi ammonite repl embed
  "com.lihaoyi" % "ammonite" % "1.1.2" % "test" cross CrossVersion.full

)

//ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

resolvers ++= Seq(
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Secured Central Repository" at "https://repo1.maven.org/maven2",
  Resolver.sonatypeRepo("snapshots")
)

// scalariform
//scalariformSettings

// ScalariformKeys.preferences := ScalariformKeys.preferences.value
//   .setPreference(AlignSingleLineCaseStatements, true)
//   .setPreference(DoubleIndentClassDeclaration, true)
//   .setPreference(IndentLocalDefs, true)
//   .setPreference(IndentPackageBlocks, true)
//   .setPreference(IndentSpaces, 2)
//   .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)

// ammonite repl
sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main().run() }""")
  Seq(file)
}.taskValue

// Optional, required for the `source` command to work
// (fullClasspath in Test) ++= {
//   (updateClassifiers in Test).value
//     .configurations
//     .find(_.configuration == Test.name)
//     .get
//     .modules
//     .flatMap(_.artifacts)
//     .collect{case (a, f) if a.classifier == Some("sources") => f}
// }
