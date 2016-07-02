package technology.walrath.baconfinder.api.parsers

import technology.walrath.baconfinder.model.Deal
import scala.io.Source
import scala.util.{Failure, Success, Try}
import spray.json._
//import DefaultJsonProtocol._
import spray.json.lenses.JsonLenses._
import spray.json.DefaultJsonProtocol._

/**
  * Created by justin on 7/2/16.
  */
class MeijerAPIParser extends APIParser {
  val meatAPIUrl = "https://api2.shoplocal.com/retail/89eece5eb3aae72b/2013.1/json/Categorylistings?storeid=2595133&categoryid=403117&preferredlanguage=1&imagesize=200&listingcount=999&listingsortmode=23&listingindex=1&sneakpeek=n&reviewsflag=n"

  override def parse = Try{ Source.fromURL(meatAPIUrl).mkString } match {
    case Success(result) => {
      val json = result.parseJson
      json.extract[JsValue]('Results / *).map(deal =>
        Deal("Meijer", deal.extract[String]('Title), deal.extract[String]('Deal), deal.extract[String]('ListingStartDateString), deal.extract[String]('ListingEndDateString))
      ).toList
    }
    case Failure(e) => {
      e.printStackTrace(); List.empty
    }
  }
}
