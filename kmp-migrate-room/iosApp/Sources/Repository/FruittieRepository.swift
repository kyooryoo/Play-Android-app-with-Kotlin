import ConcurrencyExtras
import sharedKit

protocol FruittieRepository {
    func getData() -> AsyncStream<[Fruittie]>
}

class DefaultFruittieRepository: FruittieRepository {
    private let fruittieDao: any FruittieDao
    private let api: FruittieApi

    init(fruittieDao: any FruittieDao, api: FruittieApi) {
        self.fruittieDao = fruittieDao
        self.api = api
    }

    func getData() -> AsyncStream<[Fruittie]> {
        let dao = fruittieDao
        Task {
            let isEmpty = try await dao.count() == 0
            if isEmpty {
                let response = try await api.getData(pageNumber: 0)
                let fruitties = response.feed.map {
                    FruittieEntity(
                        id: 0,
                        name: $0.name,
                        fullName: $0.fullName,
                        calories: ""
                    )
                }
                _ = try await dao.insert(fruitties: fruitties)
            }
        }
        return dao.getAll().map { entities in
            entities.map(Fruittie.init(entity:))
        }.eraseToStream()
    }
}
