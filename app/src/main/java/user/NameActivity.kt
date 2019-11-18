package user

import `interface`.StoreState
import actions.Actions
import models.Store
import models.StoreType

class NameStore(private val store: List<Store>, private val newStoreType: StoreType):
    StoreState {
    override fun consumeAction(action: Actions): StoreState {
        return when (action) {
            is Actions.SubmitStoreClicked -> {
                val newSandwich = Store(action.StoreName, newStoreType)
                StoreList(store + newSandwich)
            }
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}