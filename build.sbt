import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import interplay.ScalaVersions._
import scalariform.formatter.preferences._

val scala213Version = "2.13.0-M5"

val specsVersion = "4.3.6"
val specsBuild = Seq(
  "specs2-core",
  "specs2-junit",
  "specs2-mock"
).map("org.specs2" %% _ % specsVersion)

val formattingSettings = Seq(
  scalariformAutoformat := true,
  ScalariformKeys.preferences := ScalariformKeys.preferences.value
    .setPreference(SpacesAroundMultiImports, true)
    .setPreference(SpaceInsideParentheses, false)
    .setPreference(DanglingCloseParenthesis, Preserve)
    .setPreference(PreserveSpaceBeforeArguments, true)
    .setPreference(DoubleIndentConstructorArguments, true)
)

lazy val `play-iteratees` = project
  .in(file("iteratees"))
  .enablePlugins(PlayLibrary)
  .settings(formattingSettings)
  .settings(
    scalaVersion := scala212,
    crossScalaVersions := Seq(scala212, scala211),
    libraryDependencies ++= Seq(
      "org.scala-stm" %% "scala-stm" % "0.9"
    ) ++ specsBuild.map(_ % Test)
  )

lazy val `play-iteratees-reactive-streams` = project
  .in(file("streams"))
  .enablePlugins(PlayLibrary)
  .settings(Defaults.itSettings, formattingSettings)
  .settings(
    scalaVersion := scala212,
    crossScalaVersions := Seq(scala212, scala211),
    libraryDependencies ++= Seq(
      "org.reactivestreams" % "reactive-streams" % "1.0.2"
    ) ++ specsBuild.map(_ % Test)
  ).dependsOn(`play-iteratees`)

lazy val `play-iteratees-root` = (project in file("."))
  .enablePlugins(PlayRootProject)
  .aggregate(`play-iteratees`, `play-iteratees-reactive-streams`)
  .settings(
    scalaVersion := scala212,
    crossScalaVersions := Seq(scala212, scala211)
  )
playBuildRepoName in ThisBuild := "play-iteratees"
