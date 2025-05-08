lazy val root = project
  .in(file("."))
  .settings(
    scalaVersion := "3.6.4",
    name := "structure",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.1.1" % Test,
      "io.github.neotypes" %% "neotypes-core" % "1.2.1",
      "org.neo4j.driver" % "neo4j-java-driver" % "5.28.5"
    )
  )
