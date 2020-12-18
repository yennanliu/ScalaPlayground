import org.scalatest.flatspec.AnyFlatSpec

// https://docs.scala-lang.org/tour/case-classes.html

// UDF
import schema.adhocSchema

class SchemaTest extends AnyFlatSpec{

  "Book isbn" should "equal as expect" in {
    val book = adhocSchema.Book("123")
    assert (book.isbn === "123")
  }

  "Message sender, recipient, body" should "equal as expect" in {
    val msg = adhocSchema.Message("tom", "jim", "hello there")
    assert (msg.sender === "tom")
    assert (msg.recipient === "jim")
    assert (msg.body === "hello there")
  }

  "Message's values and it copy values" should "equal as expect" in {
    val msg2 = adhocSchema.Message("julien@bretagne.fr", "travis@washington.us", "Me zo o komz gant ma amezeg")
    val msg3 = msg2.copy(sender = msg2.recipient, recipient = "claire@bourgogne.fr")
    assert (msg3.sender === "travis@washington.us")
    assert (msg3.recipient === "claire@bourgogne.fr")
    assert (msg3.body === "Me zo o komz gant ma amezeg")
  }
}
