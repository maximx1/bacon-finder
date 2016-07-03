package technology.walrath.baconfinder.formatters
import technology.walrath.baconfinder.model.Deal

/**
  * Created by justin on 7/2/16.
  */
object CommandLineOutputFormatter extends Formatter {
  override def format(deal: Deal): String =
    "Title: " + deal.title +
      "\n\tStore: " + deal.storeInfo.name +
      "\n\tAddress: " + deal.storeInfo.address +
      "\n\tDeal: " + deal.deal +
      "\n\tStart: " + deal.start +
      "\n\tEnd: " + deal.end
}
