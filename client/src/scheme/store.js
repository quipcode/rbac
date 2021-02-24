// Imports
import { createStore, combineReducers, applyMiddleware } from 'redux'
import thunk from 'redux-thunk'

// App Imports

import base from '../modules/core/base/api/state'
import user from '../modules/core/user/api/state'

// Root Reducer
const rootReducer = combineReducers({
    base,
    ...user,
})

// Store
export const store = createStore(rootReducer, applyMiddleware(thunk))