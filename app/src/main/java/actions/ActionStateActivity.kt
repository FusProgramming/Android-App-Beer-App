package actions

import models.Store
import models.StoreType

sealed class Actions {
    class AddStoreClicked: Actions()
    class HideStoreClicked: Actions()
    class StoreTypeSelected(val type: StoreType): Actions()
    class PredefinedStoreSelected(val store: MutableList<Store>): Actions()
    class SubmitStoreClicked(val StoreName: String): Actions()
}