import { applyMiddleware, combineReducers, legacy_createStore } from "redux"
import thunk from 'redux-thunk'; 
import { authReducer } from "./Auth/Reducer";

const rootReducers = combineReducers({

    auth: authReducer,
     
});

export const store = legacy_createStore(rootReducers, applyMiddleware(thunk)); // for async actions

// import { configureStore } from '@reduxjs/toolkit';
// import { authReducer } from './Auth/Reducer';

// export const store = configureStore({
//   reducer: {
//     auth: authReducer,
//   },
//   // thunk is included by default
// });