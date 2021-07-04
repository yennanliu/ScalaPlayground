package com.yen.customerCRM.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}

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
        customerService.getCustomer(userId.toInt)
    }
  }
}
