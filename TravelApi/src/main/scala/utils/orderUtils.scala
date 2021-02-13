package utils

import base.orderUtilsBase

class orderUtil extends orderUtilsBase {
  override def checkOrder(orderId:String): Unit = {
    println(s"this is order detail :  , orderId = $orderId")
  }

  override def makeOrder(userId: String): Unit = {
    println(s"user $userId make order!")
  }
}
