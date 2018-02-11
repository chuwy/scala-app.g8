import sbt._

object Dependencies {

  object V {
    // Scala
    val scopt            = "3.6.0"
    val cats             = "1.0.1"
    val newts            = "0.1.1"
    val circe            = "0.9.0"
    val http4s           = "0.18.0"
    // Scala (test only)
    val specs2           = "4.0.2"
    val scalaCheck       = "1.13.4"
  }

  // Scala
  val scopt            = "com.github.scopt"           %% "scopt"                % V.scopt
  val circe            = "io.circe"                   %% "circe-core"           % V.circe
  val circeParser      = "io.circe"                   %% "circe-parser"         % V.circe
  val circeGeneric     = "io.circe"                   %% "circe-generic"        % V.circe
  val newts            = "com.github.julien-truffaut" %%  "newts-core"          % V.newts
  val cats             = "org.typelevel"              %% "cats-core"            % V.cats
  val http4sBlaze      = "org.http4s"                 %% "http4s-blaze-server"  % V.http4s
  val http4sCirce      = "org.http4s"                 %% "http4s-circe"         % V.http4s
  val http4sDsl        = "org.http4s"                 %% "http4s-dsl"           % V.http4s
  // Scala (test only)
  val specs2           = "org.specs2"                 %% "specs2-core"          % V.specs2         % "test"
  val scalaCheck       = "org.scalacheck"             %% "scalacheck"           % V.scalaCheck     % "test"
}
