package com.yen.customerCRM.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import com.yen.customerCRM.bean.Customer
import com.yen.customerCRM.model.{HiRequest, customerInfo, userUpdateRequest}
import com.yen.customerCRM.service.CustomerService

object AppController {

  val customerService = new CustomerService

  class getUsers extends Controller {
    get("/api/v1/users") {
      requests: Request =>
        customerService.list()
    }
  }

  class getUser extends Controller {
    get("/api/v1/user/:userId") {
      requests: Request =>
        val userId = requests.params("userId")
        Some(customerService.getCustomer(userId.toInt))
    }
  }


  class modifyUser extends Controller {
    post("/api/v1/update") { requests: customerInfo =>
      val updatedCustomer = new Customer(requests.id, requests.name, requests.gender, requests.age, requests.tel, requests.email)
      println("*** updatedCustomer = " + updatedCustomer.toString)
      customerService.modify(requests.id, updatedCustomer)
    }
  }

  class deleteUser extends Controller {
    post("/api/v1/delete/:userId") {
      requests:Request =>
        val userId = requests.params("userId")
        Some(customerService.delete(userId.toInt))
    }
  }

  class postHelloWorld extends Controller {
    post("/hi") { hiRequest: HiRequest =>
      "Hello " + hiRequest.name + " with id " + hiRequest.id
    }
  }
}
