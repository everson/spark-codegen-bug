package everson.sparkcodegen

import org.specs2.mutable.Specification

class FunctionsSpec extends Specification with SparkSessionTestWrapper {

  import FunctionsSpec._
  import spark.implicits._

  "run" should {
    "pass on public case class" in {

      val data = Seq(
        NumBool(1, false),
        NumBool(2, true),
        NumBool(3, false)
      ).toDF

      val size = data.collect().toList.size
      size must beEqualTo(3)

    }

    "Fail on private case class" in {

      val data = Seq(
        PrivNumBool(1, false),
        PrivNumBool(2, true),
        PrivNumBool(3, false)
      ).toDF

      val size = data.collect().toList.size
      size must beEqualTo(3)

    }

  }

}

object FunctionsSpec {

  case class NumBool(someNum: Int, expected: Boolean)

  private case class PrivNumBool(someNum: Int, expected: Boolean)

}
