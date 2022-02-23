import org.scalatest.flatspec.AnyFlatSpec
import utils.MsgChecker

class MsgCheckerTest extends AnyFlatSpec{

  val msg_checker = new MsgChecker

  "httpStatus" should "equal as expect" in {
    val httpmsg1 = "SUCCESS"
    assert (msg_checker.CheckSuccess (httpmsg1) === true)

    val httpmsg2 = "FAIL"
    assert (msg_checker.CheckSuccess (httpmsg2) === false)

    val httpmsg3 = "this is not validated httpmsg"
    assert (msg_checker.CheckSuccess (httpmsg3) === false)
  }

  "httpStatusCode" should "equal as expect" in {
    val httpCode1 = 201
    assert (msg_checker.CheckHttpStatusCode (httpCode1) === true)

    val httpCode2 = 404
    assert (msg_checker.CheckHttpStatusCode (httpCode2) === false)

    val httpmsg3 = "this is not validated httpmsg"
    assert (msg_checker.CheckSuccess (httpmsg3) === false)
  }

  "List1 length" should "equal as expect" in {
    val myList_1 = List(1,2,3)
    assert (msg_checker.CheckListLength (myList_1) === "equals or larger than 3")
  }

  "List2 length" should "equal as expect" in {
    val myList_2 = List(1,2)
    assert (msg_checker.CheckListLength (myList_2) === "equals 2")
  }

  "List3 length" should "equal as expect" in {
    val myList_3 = List(1)
    assert (msg_checker.CheckListLength (myList_3) ===  "equals 1")
  }
}
