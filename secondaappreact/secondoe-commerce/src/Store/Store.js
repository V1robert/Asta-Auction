import { configureStore } from "@reduxjs/toolkit"
import  userReducer  from '../Store/Slices/userSlices'
import spinnerReducer from '../Store/Slices/spinnerSlices'
import pagineReducer from '../Store/Slices/pagineSlices'
import fileReducer from '../Store/Slices/fileTornati'
import createSagaMiddleware from 'redux-saga'

import {mySaga} from '../sagas'

const sagaMiddleware = createSagaMiddleware()
const middleware = [sagaMiddleware]
export const store =configureStore({
    reducer: {
        user:userReducer,
        spinner:spinnerReducer,
        pagine:pagineReducer,
        file:fileReducer
        
    },
    middleware: (getDefaultMiddleware) =>
          getDefaultMiddleware().concat(middleware),
})

sagaMiddleware.run(mySaga)