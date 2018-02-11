// SBT
import sbt._
import Keys._

// sbt-assembly
import sbtassembly._
import sbtassembly.AssemblyKeys._


/**
 * To enable any of these you need to explicitly add Settings value to build.sbt
 */
object BuildSettings {

  // Makes package (build) metadata available withing source code
  lazy val scalifySettings = Seq(
    sourceGenerators in Compile += Def.task {
      val file = (sourceManaged in Compile).value / "settings.scala"
    IO.write(file, """package $organization$.$name;format="lower,word"$.generated
                      |object ProjectMetadata {
                      |  val version = "%s"
                      |  val name = "%s"
                      |  val organization = "%s"
                      |  val scalaVersion = "%s"
                      |}
                      |""".stripMargin.format(version.value, name.value, organization.value, scalaVersion.value))
      Seq(file)
    }.taskValue
  )

  lazy val buildSettings = Seq[Setting[_]](
    scalacOptions := Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-unchecked",
      "-Ywarn-dead-code",
      "-Ywarn-inaccessible",
      "-Ywarn-infer-any",
      "-Ywarn-nullary-override",
      "-Ywarn-nullary-unit",
      "-Ywarn-numeric-widen",
      "-Ywarn-unused",
      "-Ywarn-value-discard",
      "-Ypartial-unification",
      "language:higherKinds"
    ),
    javacOptions := Seq(
      "-source", "1.8",
      "-target", "1.8",
      "-Xlint"
    ),
    addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
  )

  // sbt-assembly settings
  lazy val assemblySettings = Seq(
    assemblyJarName in assembly := { moduleName.value + "-" + version.value + ".jar" },

    assemblyShadeRules in assembly := Seq(
      ShadeRule.rename(
        "com.amazonaws.**" -> "shadeaws.@1",
        "org.apache.http.**" -> "shadehttp.@1"
      ).inAll
    ),

    assemblyExcludedJars in assembly := {
      val cp = (fullClasspath in assembly).value
      val excludes = Set(
        "jasper-compiler-5.5.12.jar",
        "hadoop-core-1.1.2.jar", // Provided by Amazon EMR. Delete this line if you're not on EMR
        "hadoop-tools-1.1.2.jar" // "
      )
      cp.filter { jar => excludes(jar.data.getName) }
    },

    assemblyMergeStrategy in assembly := {
      case "project.clj" => MergeStrategy.discard // Leiningen build files
      case x if x.startsWith("META-INF") => MergeStrategy.discard
      case x if x.endsWith("public-suffix-list.txt") => MergeStrategy.last
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )

  lazy val helpersSettings = Seq[Setting[_]](
    initialCommands := "import $organization$.$name;format="lower,word"$._"
  )
}
