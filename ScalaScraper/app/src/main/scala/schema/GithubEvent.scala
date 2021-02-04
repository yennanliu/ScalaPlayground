package schema

case class GithubEvent(
   id : String,
   _type: String,
   actor:  Option[Map[String, String]],
   repo : Option[Map[String, String]],
   payload: Option[Map[String, String]],
   public : Boolean,
   created_at : String)
