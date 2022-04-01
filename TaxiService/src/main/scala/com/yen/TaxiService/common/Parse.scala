package com.yen.TaxiService.common

/**
 *  func for parse files
 */


object Parse {

  // read information from file
  def parseFile(fileName:String):String ={
    val lines = scala.io.Source.fromFile(fileName).toList
    var output = ""
    lines.map{
      line =>
        val _info = line.toString
        output += (_info)
    }.flatMap(_.toString)
    output
  }

}
