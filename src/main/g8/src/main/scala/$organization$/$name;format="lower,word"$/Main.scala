package $organization$.$name;format="lower,word"$


object Main {

  /**
   * Raw CLI configuration used to extract options from command line
   * Created solely for private `rawCliConfig` value and can contain
   * incorrect state that should be handled by `transform` function
   */
  private case class CliConfig(input: String, output: String)

  /**
   * Starting raw value, required by `parser`
   */
  private val rawCliConfig = CliConfig("", "")

  /**
   * End application config parsed from CLI. Unlike `CliConfig`
   */
  case class AppConfig(input: String, output: String)


  /**
   * Check that raw config contains valid stat
   */
  def transform(raw: CliConfig): Either[String, AppConfig] = 
    Right(AppConfig(raw.input, raw.output))

  /**
   * Scopt parser providing necessary argument annotations and basic validation
   */
  private val parser = new scopt.OptionParser[CliConfig](generated.ProjectMetadata.name) {
    head(generated.ProjectMetadata.name, generated.ProjectMetadata.version)

    opt[String]('i', "input").required().
      action( (x, c) => c.copy(input = x) ).
      text("Input path")

    opt[String]('o', "output").required().
      action( (x, c) => c.copy(output = x) ).text("Input")

    help("help").text("prints this usage text")
  }


  def main(args: Array[String]): Unit = {
    parser.parse(args, rawCliConfig).map(transform) match {
      case Some(Right(appConfig)) =>
        println(appConfig)
      case Some(Left(error)) => 
        // Failed transformation
        println(error)
        sys.exit(1)
      case None =>
        // Invalid arguments
        sys.exit(1)
    }
  }
}
