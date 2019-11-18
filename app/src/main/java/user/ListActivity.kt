package user

import `interface`.StoreState
import actions.Actions
import models.Store

class StoreList(val store: List<Store>):
    StoreState {
    override fun consumeAction(action: Actions): StoreState {
        return when(action) {
            is Actions.AddStoreClicked -> AddStore(store)
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}