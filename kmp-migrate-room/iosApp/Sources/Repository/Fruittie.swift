import sharedKit

struct Fruittie: Hashable {
   let entity: FruittieEntity

   var id: Int64 {
       entity.id
   }

   var name: String? {
       entity.name
   }

   var fullName: String? {
       entity.fullName
   }
}
