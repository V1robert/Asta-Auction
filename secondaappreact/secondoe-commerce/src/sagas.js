import { all, call, put, takeEvery, takeLatest } from 'redux-saga/effects'
import { setIsLoading } from './Store/Slices/spinnerSlices'
import { inviaZip } from './ajax'
import { setContenuto, setDescirizione } from './Store/Slices/fileTornati'

  function* loadingSaga(action) {
    console.log("sto caricando"+action.payload)
    yield put (setIsLoading(true))
    
    
}
  function* fineCaricamento(action) {
    yield put (setIsLoading(false))
    console.log("ho finito di caricare "+action.payload)

}

export function* mySaga() {
    yield takeEvery('CARICAMENTO',loadingSaga)
    yield takeEvery ('FINECARICAMENTO',fineCaricamento)    
    yield takeLatest ('INVIAFILE',fetchDeiFile)
    yield takeLatest ('FETCH_OK',fetchOk )
    yield takeLatest ('FETCH_KO',fetchKo)
}

 function* fetchDeiFile(action) {
    try{
      console.log(action)
    const response = yield call(inviaZip,action.payload)

    console.log(response+""+"nel sagassas")
    yield put({type: 'FETCH_OK' ,  payload: request}); 
  
    
  }catch(error){
    yield put({type: 'FETCH_KO', error})
  }
}


function* fetchOk(response) {
  yield all([
    put(setCodice(response.codice)),
    put(setContenuto(response.contenuto)),
    put(setDescirizione(response.descrizione)),
    put(setStato(response.stato)),
  ])
  
}

function* fetchKo(response) {
  console.error(error)

}

