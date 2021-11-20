package com.yen.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}
import com.yen.app.tool.collectSparkDF
import com.yen.dev.SparkBatch
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object AppController {

  class hello extends Controller {
    get("/hello") {request: Request => "hello from Finatra Server !!!"}
  }

  class sparkDemo extends Controller{
    get("/spark_demo") {
      val spark_batch = new SparkBatch
      SparkBatch.apply(spark_batch)
      request: Request => "spark_batch run !!!" //collectSparkDF(spark, rdd)
    }
  }
}
