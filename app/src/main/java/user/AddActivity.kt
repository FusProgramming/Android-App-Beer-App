package user

import actions.Actions
import models.Store

class AddStore(private val stores: List<Store>):
    StoreState {
    override fun consumeAction(action: Actions): StoreState {
        return when (action) {
            is Actions.StoreTypeSelected -> NameStore(stores, action.type)
            is Actions.PredefinedSandwichSelected -> StoreList(stores + action.store)
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}