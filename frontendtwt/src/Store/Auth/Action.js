// import axios from "axios"
// import { API_BASE_URL } from "../../Config/api"
// import { LOGIN_USER_FAILURE, LOGIN_USER_SUCCESS, REGISTER_USER_SUCCESS, REGISTER_USER_FAILURE, GET_USER_PROFILE_SUCCESS, GET_USER_PROFILE_FAILURE} from './ActionType'

// export const loginUser = (loginData) => async (dispatch) =>{
//     try{
//         const {data} = await axios.post(`${API_BASE_URL}/auth/signin`, loginData)

//         console.log("loggedin user", data);

//         if(data.jwt){
//             localStorage.setItem("jwt", data.jwt)
//         }
//         dispatch({
//             type: LOGIN_USER_SUCCESS,
//             payload: data.jwt
//         })
//     } catch(error){
//         console.log("error ",error);
//         dispatch({
//             type: LOGIN_USER_FAILURE,
//             payload: error.response.data.message
//         })
//     }
// }

// export const registerUser = (registerData) => async (dispatch) =>{
//     try{
//         const {data} = await axios.post(`${API_BASE_URL}/auth/signup`, registerData)

//         console.log("registered user", data);
//         if(data.jwt){
//             localStorage.setItem("token", data.jwt)
//         }
//         dispatch({
//             type: REGISTER_USER_SUCCESS,
//             payload: data.jwt
//         })
//     } catch(error){
//         console.log("error ",error);
//         dispatch({
//             type: REGISTER_USER_FAILURE,
//             payload: error.response.data.message
//         })
//     }
// }

// export const getUser = () => async (dispatch) =>{
//     try{
//         const {data} = await axios.get(`${API_BASE_URL}/api/users/profile`, {
//             headers: {
//                 "Authorization": `Bearer ${jwt}`
//             }
//         })

//         if(data.jwt){
//             localStorage.setItem("token", data.jwt)
//         }
//         dispatch({
//             type: GET_USER_PROFILE_SUCCESS,
//             payload: data
//         })
//     } catch(error){
//         console.log("error ",error);
//         dispatch({
//             type: GET_USER_PROFILE_FAILURE,
//             payload: error.response.data.message
//         })
//     }
// }

import axios from 'axios'
import { API_BASE_URL } from '../../Config/api'
import { LOGIN_USER_FAILURE, LOGIN_USER_SUCCESS, REGISTER_USER_SUCCESS, REGISTER_USER_FAILURE, GET_USER_PROFILE_SUCCESS, GET_USER_PROFILE_FAILURE, LOGOUT} from './ActionType'

export const loginUser = (loginData) => async (dispatch) => {
    
    try {
        const { data } = await axios.post(`${API_BASE_URL}/auth/signin`, loginData)
        console.log("logedin user ", data)
        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt)
        }

        dispatch({type:LOGIN_USER_SUCCESS, payload:data.jwt})
    } catch(error) {
        console.log("error", error)
        dispatch({type:LOGIN_USER_FAILURE, payload:error.message})
    }
    
}

export const registerUser = (registerData) => async (dispatch) => {
    
    try {
        const { data } = await axios.post(`${API_BASE_URL}/auth/signup`, registerData)
        console.log("signup user ", data)
        
        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt)
        }

        dispatch({type:REGISTER_USER_SUCCESS, payload:data.jwt})
    } catch(error) {
        console.log("error", error)
        dispatch({type:REGISTER_USER_FAILURE, payload:error.message})
    }
    
}

export const getUserProfile = (jwt) => async (dispatch) => {
    
    try {
        const { data } = await axios.get(`${API_BASE_URL}/api/users/profile`, {
            headers:{
                "Authorization" : `Bearer ${jwt}`
            }
        })
        
        dispatch({type:GET_USER_PROFILE_SUCCESS, payload:data})
    } catch(error) {
        console.log("error", error)
        dispatch({type:GET_USER_PROFILE_FAILURE, payload:error.message})
    }
    
}

export const logoutUser = () => async (dispatch) => {
    localStorage.removeItem("jwt")
    dispatch({ type: LOGOUT, payload: null })
}