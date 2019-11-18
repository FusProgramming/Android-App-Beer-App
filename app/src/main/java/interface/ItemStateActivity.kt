package `interface`

import actions.Actions

interface StoreState {
    fun consumeAction(action: Actions): StoreState
}