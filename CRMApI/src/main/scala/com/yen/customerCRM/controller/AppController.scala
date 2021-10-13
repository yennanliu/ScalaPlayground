package com.yen.customerCRM.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import com.yen.customerCRM.bean.Customer
import com.yen.customerCRM.model.{HiRequest, customerInfo, userUpdateRequest}
import com.yen.customerCRM.service.CustomerService

object AppController {

  val customerService = new CustomerService

  // init
  class initUsers extends Controller {
    post("/api/v1/init") {
      requests: Request =>
        println("*** init user")
        customerService.init()
    }
  }

  // get all users
  class getUsers extends Controller {
    get("/api/v1/users") {
      requests: Request =>
        customerService.list()
    }
  }

  // get user
  class getUser extends Controller {
    get("/api/v1/user/:userId") {
      requests: Request =>
        val userId = requests.params("userId")
        Some(customerService.getCustomer(userId.toInt))
    }
  }

  // add user
  class addUser extends Controller {
    post("/api/v1/add/:userId"){ requests: Request =>
      //val newCustomer = new
      val userId = requests.params("userId")
      println("*** userId = " + userId)
      val newCustomer =  new Customer(userId.toInt, "", 'f', 0, "", "")
      customerService.add(newCustomer)
    }
  }

  // update user
  class modifyUser extends Controller {
    post("/api/v1/update") { requests: customerInfo =>
      val updatedCustomer = new Customer(requests.id, requests.name, requests.gender, requests.age, requests.tel, requests.email)
      println("*** updatedCustomer = " + updatedCustomer.toString)
      customerService.modify(requests.id, updatedCustomer)
    }
  }

  // delete user
  class deleteUser extends Controller {
    post("/api/v1/delete/:userId") {
      requests:Request =>
        val userId = requests.params("userId")
        Some(customerService.delete(userId.toInt))
    }
  }

  // test API 1
  class postHelloWorld extends Controller {
    post("/hi") { hiRequest: HiRequest =>
      "Hello " + hiRequest.name + " with id " + hiRequest.id
    }
  }

  // test API 2
  class getHelloWorld extends Controller {
    get("/api/v1") {
      requests: Request =>
        val msg = "hello from CRM API !"
        println(msg)
        msg
    }
  }
}
