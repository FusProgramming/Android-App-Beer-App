package state

import `interface`.StoreState
import actions.Actions
import models.Store
import models.StoreType

class NameStore(private val store: List<Store>):
    StoreState {
    override fun consumeAction(action: Actions): StoreState {
        return when (action) {
            is Actions.SubmitStoreClicked -> {
                val newStore = Store(action.StoreName)
                StoreList(store + newStore)
            }
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}