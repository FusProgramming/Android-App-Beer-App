package user

import `interface`.StoreState
import actions.Actions
import models.Store
import models.StoreType

class NameSandwich(private val store: List<Store>, private val newStoreType: StoreType):
    StoreState {
    override fun consumeAction(action: Actions): StoreState {
        return when (action) {
            is Actions.SubmitStoreClicked -> {
                val newSandwich = Store(action.storeName, newStoreType)
                StoreList(store + newSandwich)
            }
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}