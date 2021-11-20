//package tool
//
//import utils.orderUtils
//
//object testUserutils extends App {
//
//  // define orders
//  val o1 = new orderUtils("tim", "C-0001")
//  val o2 = new orderUtils("jack", "C-0002")
//  val o3 = new orderUtils("vivi", "C-0003")
//
//  // make orders!
//  orderUtils.makeOrder(o1)
//  orderUtils.makeOrder(o2)
//  orderUtils.makeOrder(o3)
//
////  orderUtil.orderNum
////  orderUtil.showAllOrders
//}
//
//
//import base.orderUtilsBase
//import utils.orderUtils
//
//class orderUtil extends orderUtilsBase {
//  override def checkOrder(orderId:String): Unit = {
//    println(s"this is order detail :  , orderId = $orderId")
//  }
//
//  override def makeOrder(userId: String): Unit = {
//    println(s"user $userId make order!")
//  }
//}
