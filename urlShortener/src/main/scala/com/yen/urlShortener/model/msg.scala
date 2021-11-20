package com.yen.urlShortener.model

class msg {
}

case class HiRequest(id: Long, name: String)
case class urlRequest(url:String)
case class hashCodeRequest(code:String)