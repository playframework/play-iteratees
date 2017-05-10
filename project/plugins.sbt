resolvers ++= DefaultOptions.resolvers(snapshot = true)

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")
addSbtPlugin("com.typesafe.play" % "interplay" % "1.3.5")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.1.8")

