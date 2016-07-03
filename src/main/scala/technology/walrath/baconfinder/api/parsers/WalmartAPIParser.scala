package technology.walrath.baconfinder.api.parsers

import technology.walrath.baconfinder.model.{Deal, StoreInfo}
import scala.util.{Failure, Success, Try}
import spray.json._
import spray.json.lenses.JsonLenses._
import spray.json.DefaultJsonProtocol._

/**
  * Created by justin on 7/2/16.
  */
class WalmartAPIParser extends APIParser {

  val store = StoreInfo("3459", "", "Walmart", "2225 W Market St, Bloomington IL")
  val flierIdsAPIUrl = "http://weeklyads.walmart.com/hosted/walmart?locale=en&store_code=%s"
  val adURLAPIUrl = "http://weeklyads.walmart.com/hosted/walmart/flyers/%s?locale=en&store_code=%s"

  override def parse: List[Deal] = {
    Try { getAsJson(flierIdsAPIUrl.format(store.id)) } match {
      case Success(flierIdsResponse) => {
        val flierIdsJson = flierIdsResponse.parseJson
        flierIdsJson.extract[Long](* / 'id).flatMap { flierId =>
          Try {
            getAsJson(adURLAPIUrl.format(flierId, store.id))
          } match {
            case Success(adsResponse) => {
              val adsResponseJson = adsResponse.parseJson
              val runningList = adsResponseJson.extract[JsValue]('items / *).map(deal =>
                (deal.extract[Long]('id), Deal(store, deal.extract[String]('name), "", deal.extract[String]('valid_from), deal.extract[String]('valid_to)))
              ).toMap // Part 1 with tupled id to fill in the deal

              adsResponseJson.extract[JsValue]('grid_data / element(0) / 'items / *).map { deal =>
                val dealId = deal.extract[Long]('id)
                val dealPrice = Try { deal.extract[String]('current_price) }.getOrElse("")
                runningList.get(deal.extract[Long]('id)).map(_.copy(deal = dealPrice)).getOrElse(null)
              }.toList.filter(_ != null)
            }
            case Failure(e) => {
              e.printStackTrace();
              List.empty
            }
          }
        }.toList
      }

      case Failure(e) => {
        e.printStackTrace();
        List.empty
      }
    }
  }

  @throws(classOf[java.io.IOException])
  @throws(classOf[java.net.SocketTimeoutException])
  def getAsJson(url: String): String = {
    import java.net.{URL, HttpURLConnection}
    val connection = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
    connection.setRequestProperty("Accept", "application/json")
    connection.setRequestMethod("GET")
    val inputStream = connection.getInputStream
    val content = io.Source.fromInputStream(inputStream).mkString
    if(inputStream != null) inputStream.close
    content
  }
}
