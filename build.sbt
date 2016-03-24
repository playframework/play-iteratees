lazy val `play-iteratees` = project
  .in(file("iteratees"))
  .enablePlugins(PlayLibrary)
  .settings(scalariformSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-stm" %% "scala-stm" % "0.7",
      "org.specs2" %% "specs2-core" % "3.6.6" % Test
    )
  )

lazy val `play-iteratees-reactive-streams` = project
  .in(file("streams"))
  .enablePlugins(PlayLibrary)
  .settings(scalariformSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.reactivestreams" % "reactive-streams" % "1.0.0",
      "org.specs2" %% "specs2-core" % "3.6.6" % Test
    )
  ).dependsOn(`play-iteratees`)

lazy val `play-iteratees-root` = (project in file("."))
  .enablePlugins(PlayRootProject)
  .aggregate(`play-iteratees`, `play-iteratees-reactive-streams`)
  .settings(scalaVersion := "2.11.7")

playBuildRepoName in ThisBuild := "play-iteratees"
