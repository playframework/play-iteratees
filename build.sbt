import interplay.ScalaVersions._

val scala213Version = "2.13.0-M5"

val specsVersion = "4.3.6"
val specsBuild = Seq(
  "specs2-core",
  "specs2-junit",
  "specs2-mock"
).map("org.specs2" %% _ % specsVersion)

val commonSettings = scalariformSettings ++ Seq(
  scalaVersion := scala212,
  crossScalaVersions := Seq(scala212, scala211, scala213Version),
  unmanagedSourceDirectories in Compile += {
    val sourceDir = (sourceDirectory in Compile).value
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, n)) if n >= 13 => sourceDir / "scala-2.13+"
      case _ => sourceDir / "scala-2.12-"
    }
  }
)

lazy val `play-iteratees` = project
  .in(file("iteratees"))
  .enablePlugins(PlayLibrary)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-stm" %% "scala-stm" % "0.9",
      "org.scala-lang.modules" %% "scala-collection-compat" % "0.2.1"
    ) ++ specsBuild.map(_ % Test)
  )

lazy val `play-iteratees-reactive-streams` = project
  .in(file("streams"))
  .enablePlugins(PlayLibrary)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.reactivestreams" % "reactive-streams" % "1.0.2"
    ) ++ specsBuild.map(_ % Test)
  ).dependsOn(`play-iteratees`)

lazy val `play-iteratees-root` = (project in file("."))
  .enablePlugins(PlayRootProject)
  .aggregate(`play-iteratees`, `play-iteratees-reactive-streams`)
  .settings(commonSettings: _*)

playBuildRepoName in ThisBuild := "play-iteratees"
