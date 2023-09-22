import { createSlice } from "@reduxjs/toolkit";

const pagineInitial = {
    indiceDelUltimoFile: 0,
    indiceDelPrimoFile: 0,
    fileCorrenti :0,
    fileTotali :0
}

export const pagineSlice = createSlice ({
    name:"pagine",
    initialState:pagineInitial,
    reducers: {

        setPagine:(state,action) => {
            return state=action.payload
        },

        setIndiceDelPrimoFile:(state,action) => {
            state.indiceDelPrimoFile =action.payload
        },

        setIndiceDelUltimoFile:(state,action) => {
            state.indiceDelUltimoFile =action.payload
        },

        setFileCorrenti:(state,action) => {
            state.fileCorrenti = action.payload
        },

        setFileTotali:(state,action) => {
            state.fileTotali = action.payload
        }
    }
}

)

export const {setPagine,setIndiceDelPrimoFile,setIndiceDelUltimoFile,setFileCorrenti,setFileTotali} = pagineSlice.actions

export default pagineSlice.reducer