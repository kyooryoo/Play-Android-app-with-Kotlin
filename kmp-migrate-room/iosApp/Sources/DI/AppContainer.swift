import Combine
import Foundation
import sharedKit

class AppContainer: ObservableObject {
    let dataController: DataController
    let api: FruittieApi
    let fruittieRepository: FruittieRepository
    let cartRepository: CartRepository

    init() {
        dataController = DataController()
        api = FruittieNetworkApi(
            apiUrl: URL(
                string:
                    "https://android.github.io/kotlin-multiplatform-samples/fruitties-api"
            )!)
        fruittieRepository = DefaultFruittieRepository(
            fruittieDao: dataController.database.fruittieDao(),
            api: api
        )
        cartRepository = DefaultCartRepository(
                    cartDao: dataController.database.cartDao()
        )
    }
}
