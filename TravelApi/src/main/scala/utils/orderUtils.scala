package utils

import base.orderUtilsBase

class orderUtilsxxx extends orderUtilsBase {
  override def checkOrder(orderId:String): Unit = {
    println(s"this is order detail :  , orderId = $orderId")
  }

  override def makeOrder(): Unit = {
    println(s"makeOrder OK!")
  }
}
