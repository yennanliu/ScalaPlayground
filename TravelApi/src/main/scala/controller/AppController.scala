package controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}
import utils.{userUtils}

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
    get("/add_user/:userId") {
      requests: Request =>
      val userId = requests.params("userId")
      u_utils.addNewUser(userId)
      s"new user created! $userId"
    }
  }

  class DeleteUser extends Controller {
    get("/delete_user/:userId") {
      requests: Request =>
        val userId = requests.params("userId")
        u_utils.deleteUser(userId)
        s"new user deleted! $userId"
    }
  }

  // orders
//  // TODO : fix this
//  class GetOrder extends Controller {
//      get("/order/:orderId") {
//        requests : Request =>
//          // if there is no order, init the orders
//          if ( orderUtils.orderNum == 0  ){
//            orderUtils.initOrder()
//          }
//         val orderId = requests.params("orderId")
//         //orderUtils.getOrder(orderUtils)
//      }
//  }
//
//  // TODO : fix this
//  class ShowOrders extends Controller {
//    get("/orders"){
//      // if there is no order, init the orders
//      if ( orderUtils.orderNum == 0  ){
//        orderUtils.initOrder()
//      }
//      requests : Request =>
//        orderUtils.showAllOrders()
//    }
//  }

}
