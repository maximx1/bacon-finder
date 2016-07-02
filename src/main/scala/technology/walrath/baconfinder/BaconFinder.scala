package technology.walrath.baconfinder

import technology.walrath.baconfinder.api.parsers.{APIParser, MeijerAPIParser}
import technology.walrath.baconfinder.filters.DealFilters._

/**
  * Created by justin on 7/2/16.
  */
object BaconFinder extends App {
  val apiParsers: List[APIParser] = new MeijerAPIParser :: Nil

  val deals = apiParsers.flatMap(_.parse(Bacon))
  deals.foreach(x => println("Title: " + x.title + "\n\tStore: " + x.store + "\n\tDeal: " + x.deal + "\n\tStart: " + x.start + "\n\tEnd: " + x.end))
}
