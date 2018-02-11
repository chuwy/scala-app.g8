package $organization$.$name;format="lower,word"$

import org.specs2.Specification

class CheckSpec extends Specification { def is = s2"""
  One plus one test \$e1
  """

  def e1 = {
    val result = 1 + 1
    val expected = 2
    result must beEqualTo(expected)
  }
}
