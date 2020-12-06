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
  .settings(libraryDependencies ++= Dependencies.all)
  .settings(BuildSettings.helpersSettings)

