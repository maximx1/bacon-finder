package technology.walrath.baconfinder

import technology.walrath.baconfinder.api.parsers.{APIParser, ShoplocalAPIParser}
import technology.walrath.baconfinder.filters.DealFilters._
import technology.walrath.baconfinder.formatters.CommandLineOutputFormatter

/**
  * Created by justin on 7/2/16.
  */
object BaconFinder {
  val apiParsers: List[APIParser] = new ShoplocalAPIParser :: Nil

  def main(args: Array[String]) = {
    val deals = args match {
      case x if args.length > 0 => apiParsers.flatMap((_.parse(UserDef(x(0)))))
      case _ => apiParsers.flatMap(_.parse(Bacon))
    }
    deals.foreach(x => println(CommandLineOutputFormatter.format(x)))
  }
}
