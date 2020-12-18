package utils

class MsgChecker {

  case class Message(sender: String, recipient: String, body: String)

  case class httpStatus(msg: String)

  //case class inputList[A](_list: List[A])

  def CheckSuccess(httpStatus: String):Boolean = httpStatus match {
    case "SUCCESS" => true
    case "FAIL" => false
    case _ => false
  }

  def CheckHttpStatusCode(StatusCode: Int): Boolean = StatusCode match {
    case 201 => true
    case _ => false
  }

  def CheckListLength[A](inputList: List[A]):String = inputList match {
    case w :: x :: y :: z => "equals or larger than 3"
    case inputList if {inputList.length == 2} => "equals 2"
    case x :: Nil => "equals 1"
    case _ => "others"
  }
}
