package $organization$.$name;format="lower,word"$

import cats.effect.{ IOApp, ExitCode, IO }

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    IO.pure(ExitCode.Success)

}
