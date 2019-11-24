package actions

import models.Store

//--------------------------------------------------------------------------------------------------
sealed class Actions {
    class AddStoreClicked: Actions()
    class PredefinedStoreSelected(val store: MutableList<Store>): Actions()
    class SubmitStoreClicked(val StoreName: String): Actions()
}