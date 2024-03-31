name := """recipe-manager"""
organization := "com.mimo"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.12"

enablePlugins(PlayEbean)

libraryDependencies += guice
libraryDependencies += evolutions
libraryDependencies += jdbc
libraryDependencies += "com.h2database" % "h2" % "2.1.214"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.5"
libraryDependencies += "org.fusesource.jansi" % "jansi" % "1.18"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.12"

//play.evolutions.autoApply = true

// Java project. Don't expect Scala IDE
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

EclipseKeys.preTasks := Seq(Compile / compile, Test / compile)

// Use .class files instead of generated .scala files for views and routes
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

ThisBuild / libraryDependencySchemes ++= Seq(
	"org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)

enablePlugins(JavaAppPackaging)

//import sbtassembly.AssemblyPlugin.autoImport._

//assemblyJarName in assembly := "recipes.jar"
