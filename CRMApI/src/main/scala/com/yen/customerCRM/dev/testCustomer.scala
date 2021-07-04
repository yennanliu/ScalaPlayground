package com.yen.customerCRM.dev

import com.yen.customerCRM.bean.Customer

import scala.collection.mutable.ArrayBuffer

object testCustomer extends App {
  val c1 = new Customer(1, "jim", 'm', 29, "12345", "jim@google.com")
  val c2 = new Customer(2, "Lynn", 'f', 17, "98745", "lynn@fb.com")

  println(c1)

  val c3 = new Customer()
  println("c3 = " + c3)
}
