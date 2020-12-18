package schema

object adhocSchema {
  case class Book(isbn: String)

  case class Message(sender: String, recipient: String, body: String)

  case class User(userId: String)

  case class Event(eventId: String, unixTime: Int, eventMessage: String)
}
