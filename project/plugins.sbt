// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.21")

// Defines scaffolding (found under .g8 folder)
// http://www.foundweekends.org/giter8/scaffolding.html
// sbt "g8Scaffold form"
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.16.2")
addSbtPlugin("com.github.sbt" % "sbt-eclipse" % "6.0.0")
addSbtPlugin("com.typesafe.play" % "sbt-play-ebean" % "7.0.0")
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.15.0")
addSbtPlugin("com.github.sbt" % "sbt-native-packager" % "1.9.16")

// scala-xml conflict version workaround
ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)