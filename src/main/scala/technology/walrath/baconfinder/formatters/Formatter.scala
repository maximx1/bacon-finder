package technology.walrath.baconfinder.formatters

import technology.walrath.baconfinder.model.Deal

/**
  * Created by justin on 7/2/16.
  */
trait Formatter {
  def format(deal: Deal): String
}
