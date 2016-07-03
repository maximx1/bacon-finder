package technology.walrath.baconfinder.model

case class Deal(storeInfo: StoreInfo, title: String, deal: String, start: String, end: String)

case class StoreInfo(id: String, campaignId: String, name: String, address: String)

