val specsVersion = "3.8.6"
val specsBuild = Seq(
  "specs2-core",
  "specs2-junit",
  "specs2-mock"
).map("org.specs2" %% _ % specsVersion)

lazy val `play-iteratees` = project
  .in(file("iteratees"))
  .enablePlugins(PlayLibrary)
  .settings(scalariformSettings: _*)
  .settings(
    scalaVersion := "2.12.1",
    crossScalaVersions := Seq("2.12.1", "2.11.8"),
    libraryDependencies ++= Seq(
      "org.scala-stm" %% "scala-stm" % "0.8"
    ) ++ specsBuild.map(_ % Test)
  )

lazy val `play-iteratees-reactive-streams` = project
  .in(file("streams"))
  .enablePlugins(PlayLibrary)
  .settings(scalariformSettings: _*)
  .settings(
    crossScalaVersions := Seq("2.12.1", "2.11.8"),
    scalaVersion := "2.12.1",
    libraryDependencies ++= Seq(
      "org.reactivestreams" % "reactive-streams" % "1.0.0"
    ) ++ specsBuild.map(_ % Test)
  ).dependsOn(`play-iteratees`)

lazy val `play-iteratees-root` = (project in file("."))
  .enablePlugins(PlayRootProject)
  .aggregate(`play-iteratees`, `play-iteratees-reactive-streams`)
  .settings(
    scalaVersion := "2.12.1",
    crossScalaVersions := Seq("2.12.1", "2.11.8")
  )

playBuildRepoName in ThisBuild := "play-iteratees"
