package com.yen.Caliban3

import caliban.client.CalibanClientError.DecodingError
import caliban.client.FieldBuilder._
import caliban.client._
import caliban.client.__Value._

object PeopleClient {

  sealed trait Origin extends scala.Product with scala.Serializable
  object Origin {
    case object BELT  extends Origin
    case object EARTH extends Origin
    case object MARS  extends Origin

    implicit val decoder: ScalarDecoder[Origin] = {
      case __StringValue("BELT")  => Right(Origin.BELT)
      case __StringValue("EARTH") => Right(Origin.EARTH)
      case __StringValue("MARS")  => Right(Origin.MARS)
      case other                  => Left(DecodingError(s"Can't build Origin from input $other"))
    }
    implicit val encoder: ArgEncoder[Origin]    = {
      case Origin.BELT  => __EnumValue("BELT")
      case Origin.EARTH => __EnumValue("EARTH")
      case Origin.MARS  => __EnumValue("MARS")
    }
  }

  // TODO : fix below
  type CarAttributes
  object CarAttributes {
    def seats: SelectionBuilder[CarAttributes, Int]    = _root_.caliban.client.SelectionBuilder.Field("seats", Scalar())
    def color: SelectionBuilder[CarAttributes, String] = _root_.caliban.client.SelectionBuilder.Field("color", Scalar())
    def doors: SelectionBuilder[CarAttributes, Int]    = _root_.caliban.client.SelectionBuilder.Field("doors", Scalar())
    def transmissionType: SelectionBuilder[CarAttributes, String] =
      _root_.caliban.client.SelectionBuilder.Field("transmissionType", Scalar())
    def licensePlate: SelectionBuilder[CarAttributes, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("licensePlate", OptionOf(Scalar()))
    def fillLevel: SelectionBuilder[CarAttributes, Option[Int]] =
      _root_.caliban.client.SelectionBuilder.Field("fillLevel", OptionOf(Scalar()))
    def fuel: SelectionBuilder[CarAttributes, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("fuel", OptionOf(Scalar()))
  }

}
