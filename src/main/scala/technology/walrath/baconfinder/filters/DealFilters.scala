package technology.walrath.baconfinder.filters

import technology.walrath.baconfinder.model.Deal

/**
  * Created by justin on 7/2/16.
  */
object DealFilters {
  def Bacon(deals: List[Deal]): List[Deal] = deals.filter(_.title.toLowerCase().contains("bacon"))
}
