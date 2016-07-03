package technology.walrath.baconfinder.model

case class Deal(storeInfo: ShoplocalStoreInfo, title: String, deal: String, start: String, end: String)

case class ShoplocalStoreInfo(id: String, campaignId: String, name: String, address: String)
