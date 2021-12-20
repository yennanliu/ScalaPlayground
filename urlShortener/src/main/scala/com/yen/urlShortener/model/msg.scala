package com.yen.urlShortener.model

/** case class for customized data structure */

class msg {
}

case class HiRequest(id: Long, name: String)
case class urlRequest(url:String)
case class hashCodeRequest(code:String)
case class redisKeyRequest(key:String)