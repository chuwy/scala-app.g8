lazy val root = project.in(file("."))
  .settings(
    name := "$name;format="lower,word"$",
    version := "$version$",
    organization := "$organization$",
    scalaVersion := "2.13.4",
    initialCommands := "import $organization$.$name;format="lower,word"$._"
  )
  .settings(BuildSettings.assemblySettings)
  .settings(BuildSettings.buildSettings)
  .settings(BuildSettings.scalifySettings)
  .settings(
    resolvers ++= Seq(
      "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
    ),
    libraryDependencies ++= Seq(
      Dependencies.decline,
      Dependencies.circe,
      Dependencies.circeParser,
      Dependencies.circeGeneric,
      Dependencies.http4sBlaze,
      Dependencies.http4sCirce,
      Dependencies.http4sDsl,

      Dependencies.specs2,
      Dependencies.scalaCheck
    )
  )
  .settings(BuildSettings.helpersSettings)

