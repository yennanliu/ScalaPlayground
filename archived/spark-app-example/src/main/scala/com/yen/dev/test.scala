package com.yen.dev

import com.yen.dev.SparkBatch

object test extends App {
  println("test start ...")
  val spark_batch = new SparkBatch
  SparkBatch.apply(spark_batch)
  println("test end ...")
}
