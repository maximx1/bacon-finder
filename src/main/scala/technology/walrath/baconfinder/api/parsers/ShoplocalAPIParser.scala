package technology.walrath.baconfinder.api.parsers

import technology.walrath.baconfinder.model._
import scala.io.Source
import scala.util.{Failure, Success, Try}
import spray.json._
import spray.json.lenses.JsonLenses._
import spray.json.DefaultJsonProtocol._

/**
  * Created by justin on 7/2/16.
  */
class ShoplocalAPIParser extends APIParser {
  protected val stores = List(// Big stores should have the same lists in the region do only 1 at a time.
    ShoplocalStoreInfo("2624382", "890be739b05081c2", "Aldi", "1025 Wylie Drive, Bloomington, IL 61705-9341"),
    ShoplocalStoreInfo("2595133", "89eece5eb3aae72b", "Meijer", "301 Greenbriar Drive, Normal, IL 61761-6253"),
    ShoplocalStoreInfo("2596482", "e4d5e311cadb36cb", "Walgreens", "909 S Main st, Bloomington, IL 61701"),
    ShoplocalStoreInfo("2506446", "187973615c5c071a", "CVS", "210 North Center, Bloomington, IL 61701")
  )

  protected val allItemsAPIUrl = "https://api2.shoplocal.com/retail/%s/2013.1/json/Alllistings?storeid=%s&preferredlanguage=1&imagesize=200&listingcount=999&listingsortmode=23&listingindex=1&sneakpeek=n&reviewsflag=n"

  override def parse = stores.flatMap{ store => {
    Try { Source.fromURL(allItemsAPIUrl.format(store.campaignId, store.id)).mkString } match {
      case Success(result) => {
        val json = result.parseJson
        json.extract[JsValue]('Results / *).map(deal =>
          Deal(store, deal.extract[String]('Title), deal.extract[String]('Deal), deal.extract[String]('ListingStartDateString), deal.extract[String]('ListingEndDateString))
        ).toList
      }
      case Failure(e) => {
        e.printStackTrace();
        List.empty
      }
    }
  }}
}
