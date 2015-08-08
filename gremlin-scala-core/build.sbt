name := "gremlin-scala"
organization := "com.michaelpollmeier"
licenses +=("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
homepage := Some(url("https://github.com/mpollmeier/gremlin-scala"))

version := "3.0.0-SNAPSHOT"
scalaVersion := "2.11.7"
crossScalaVersions := Seq("2.10.5", scalaVersion.value)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full)

libraryDependencies <++= scalaVersion { scalaVersion =>
  val gremlinVersion = "3.0.0-incubating"
  Seq(
    "org.apache.tinkerpop" % "gremlin-core" % gremlinVersion exclude("org.slf4j", "slf4j-log4j12"),
    "org.apache.tinkerpop" % "tinkergraph-gremlin" % gremlinVersion exclude("org.slf4j", "slf4j-log4j12"),
    "org.scala-lang" % "scala-reflect" % scalaVersion,
    "com.novocode" % "junit-interface" % "0.11" % "test->default",
    "com.chuusai" %% "shapeless" % "2.2.4",
    "org.apache.tinkerpop" % "gremlin-test" % gremlinVersion % Test,
    "junit" % "junit" % "4.12" % Test,
    "com.thinkaurelius.titan" % "titan-berkeleyje" % "0.9.0-M2",
    "org.scalatest" %% "scalatest" % "2.2.5" % Test
  )
}
resolvers += "Apache public" at "https://repository.apache.org/content/groups/public/"

scalacOptions ++= Seq(
  "-Xlint"
  // "-Xfatal-warnings",
  // "-feature"
  // "-deprecation", //hard to handle when supporting multiple scala versions...
  //"-Xlog-implicits"
  //"-Ydebug"
)
// testOptions in Test += Tests.Argument("-oF") // full stack traces
incOptions := incOptions.value.withNameHashing(true) // doesn't work on travis ;(

publishTo := {
  val sonatype = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at sonatype + "content/repositories/snapshots")
  else
    Some("releases" at sonatype + "service/local/staging/deploy/maven2")
}
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ ⇒ false }
pomExtra :=
  <scm>
    <url>git@github.com:mpollmeier/gremlin-scala.git</url>
    <connection>scm:git:git@github.com:mpollmeier/gremlin-scala.git</connection>
  </scm>
    <developers>
      <developer>
        <id>mpollmeier</id>
        <name>Michael Pollmeier</name>
        <url>http://www.michaelpollmeier.com</url>
      </developer>
    </developers>
