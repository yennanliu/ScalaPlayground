package ink.baixin.producer.writer

import java.nio.ByteBuffer

object ProducerEvent {
  def apply(partitionKey: String, payload: String): ProducerEvent = {
    val record = ByteBuffer.wrap(payload.getBytes("UTF-8"))
    ProducerEvent(partitionKey, record)
  }
}

case class ProducerEvent(partitionKey: String, payload: ByteBuffer, var retryTime: Int = 0)