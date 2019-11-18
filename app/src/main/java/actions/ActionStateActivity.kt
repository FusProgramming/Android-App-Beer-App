package actions

import models.Store
import models.StoreType

sealed class Actions {
    class AddStoreClicked: Actions()
    class StoreTypeSelected(val type: StoreType): Actions()
    class PredefinedSandwichSelected(val store: Store): Actions()
    class SubmitStoreClicked(val storeName: String): Actions()
}