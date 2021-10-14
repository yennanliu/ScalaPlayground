package com.yen.urlShortener.model

class msg {

}

case class HiRequest(id: Long, name: String)

case class urlRequest(url:String, createdTime:java.sql.Timestamp)