package com.yen.heroAPI2.model

case class profile(
                  str:Int,
                  int:Int,
                  agi:Int,
                  luk:Int
                  ) {
  def apply(str:Int,
            int:Int,
            agi:Int,
            luk:Int): profile = ???

}
