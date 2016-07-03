package technology.walrath.baconfinder.filters

import technology.walrath.baconfinder.model.Deal

/**
  * Created by justin on 7/2/16.
  */
object DealFilters {
  def Bacon(deals: List[Deal]): List[Deal] = deals.filter(_.title.toLowerCase().contains("bacon"))
  def UserDef(definition: String): (List[Deal]) => List[Deal] =
    (deals: List[Deal]) => deals.filter(_.title.toLowerCase().contains(definition.toLowerCase()))
}
