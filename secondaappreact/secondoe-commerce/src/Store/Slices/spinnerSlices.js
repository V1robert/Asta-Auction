import { createSlice } from "@reduxjs/toolkit";

const spinnerInitial = {
    isLoading:false
}

export const spinnerSlice = createSlice ({
    name:"spinner",
    initialState:spinnerInitial,
    reducers: {

        setIsLoading:(state,action) => {
            state.isLoading=action.payload
        }
    }
}

)

export const {setIsLoading} = spinnerSlice.actions

export default spinnerSlice.reducer