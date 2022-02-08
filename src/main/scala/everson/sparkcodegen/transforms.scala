package everson.sparkcodegen

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object transforms {

  def publicTransform()(df: DataFrame): DataFrame = {
    df.withColumn("happy", lit("data is fun"))
  }

  private def PrivateTransform()(df: DataFrame): DataFrame = {
    df.withColumn("happy", lit("data is fun"))
  }

}
