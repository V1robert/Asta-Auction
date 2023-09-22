import { createSlice } from "@reduxjs/toolkit";

const fileTornatiInitial = {
    codice: 0,
    contenuto: [],
    descrizione :"",
    stato :""
}

export const fileTornatiSlice = createSlice ({
    name:"file",
    initialState:fileTornatiInitial,
    reducers: {

        setFileTornati:(state,action) => {
            return state=action.payload
        },

        setCodice:(state,action) => {
            state.codice = action.payload
        },

        setContenuto:(state,action) => {
            state.contenuto = action.payload
        },
        setDescirizione: (state, action) => {
            state.descrizione = action.payload
        },
        setStato: (state,action) => {
            state.stato = action.payload
        }
    }
}
)

export const {setFileTornati,setCodice,setContenuto,setDescirizione,setStato} =fileTornatiSlice.actions

export default fileTornatiSlice.reducer