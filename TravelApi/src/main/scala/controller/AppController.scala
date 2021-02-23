package controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}

import utils.{userUtils, orderUtils}

object AppController {

  // users

  val u_utils = new userUtils

  val users = List()

  class ControllerTest extends Controller {
    get("/hello") {request: Request => "hello from Finatra Server !!!"}
  }

  class GetUser extends Controller {
    get("/user/:userId") {
      requests: Request =>
      val userId = requests.params("userId")
      u_utils.getUser(userId)
    }
  }

  class GetAllUsers extends Controller {
    get("/users") {request: Request =>  u_utils.getAllUsers()}
  }

  class AddNewUser extends Controller {
    get("/add_users/:userId") {
      requests: Request =>
      val userId = requests.params("userId")
      u_utils.addNewUser(userId)
      s"new user created! $userId"
    }
  }

  // orders
  // init orders
  // TODO : fix this, make its as default/automatic process
  val o1 = new orderUtils("u0001", "C-0001")
  val o2 = new orderUtils("u0002", "C-0002")
  val o3 = new orderUtils("u0003", "C-0003")

  orderUtils.makeOrder(o1)
  orderUtils.makeOrder(o2)
  orderUtils.makeOrder(o3)

  // TODO : fix this
  class GetOrder extends Controller {
      get("/order/:orderId") {
        requests : Request =>
          val orderId = requests.params("orderId")
          orderUtils.getOrder(o1)
      }
  }

  class ShowOrders extends Controller {
    get("/orders"){
      requests : Request =>
        orderUtils.showAllOrders()
    }
  }

}
