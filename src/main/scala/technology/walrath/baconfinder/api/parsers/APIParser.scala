package technology.walrath.baconfinder.api.parsers

import technology.walrath.baconfinder.model.Deal

/**
  * Created by justin on 7/2/16.
  */
trait APIParser {
  def parse(filter: (List[Deal]) => List[Deal]) : List[Deal] = filter(parse)
  def parse: List[Deal]
}
