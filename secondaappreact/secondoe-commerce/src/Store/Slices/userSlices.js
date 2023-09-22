import { createSlice } from "@reduxjs/toolkit";

const userInitial = {
    name :"",
    surname:"",
    email:"",
    password:"",
    age:0,
    gender:""
}

export const userSlice = createSlice ({
    name:"user",
    initialState:userInitial,
    reducers: {

        setUser:(state,action) => {
            return state=action.payload
        },

        setName:(state,action) => {
            return state =action.payload
        },

        setSurname:(state,action) => {
            return state =action.payload
        }
    }
}

)

export const {setName,setSurname,setUser} = userSlice.actions

export default userSlice.reducer

