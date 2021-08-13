package com.yen.app.tool

import com.yen.dev.SparkBatchRunner.sc
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

class collectSparkDF{

}

object collectSparkDF {
  def apply(spark: SparkSession,  rdd:RDD[(String, Array[Double])]): DataFrame = {
    spark.createDataFrame(rdd)
  }

}
