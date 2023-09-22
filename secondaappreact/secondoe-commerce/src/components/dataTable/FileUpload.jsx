import React, { useEffect } from "react";
import { useState } from "react";
import { inviaZip } from "../../ajax";
import { Link } from "react-router-dom";
import '../../App.css'
import { useDispatch } from "react-redux";
import { setIsLoading } from "../../Store/Slices/spinnerSlices";
import fileDownload from "js-file-download";

import { useSelector } from 'react-redux';
function FileUpload(){
  
  const fileTornato = useSelector((state)=> state.file)

const [file,setFile]=useState();
const [noomeFile,setNomeFile] = useState();
const [content,setContent] = useState();
const [errore,setErrore] = useState("");
const [risposta,setRisposta] = useState("")
const [fileDaScaricare,setFileDaScaricare] = useState([])

const FileCaricato = {
  nomeFile:'',
  contentType:'',
  content:''
}




const dispatch = useDispatch()





function toBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      let encoded = reader.result.toString().replace(/^data:(.*,)?/, '');
      if ((encoded.length % 4) > 0) {
        encoded += '='.repeat(4 - (encoded.length % 4));
      }
      resolve(encoded);
    };
    reader.onerror = error => reject(error);
  });
}





   
function decodificaIlContenuto() {
  for(let i =0;i<fileDaScaricare.length;i++){
  let stringaDecodata = atob(fileDaScaricare[0].byteContenuto)
  
  }
}
  
 

async function Main(file){
    
  dispatch({type:'CARICAMENTO',payload:1})
  //dispatch(setIsLoading(true));
  
  FileCaricato.contentType=content
  FileCaricato.nomeFile=noomeFile
  FileCaricato.content=await toBase64(file)
  
  console.log(FileCaricato)
  
 dispatch({type:'INVIAFILE',payload:FileCaricato})
 //   const response = await inviaZip(FileCaricato)
   // .catch(function (error) {
     // dispatch({type:'FINECARICAMENTO',payload:1})
     // dispatch(setIsLoading(false));
      //if (error.response) {
       // console.log(error.response.data)
        //setErrore(response.data)
        //console.log(error.response.status)
        //console.log(error.response.headers)
      //} else if (error.request) {
       // console.log(FileCaricato)
       // console.log(error.request)
        //console.log('errore nel invio della richiesta')

      //} 
      
    //});
    console.log(fileTornato)
    setRisposta(fileTornato.descrizione)
    setFileDaScaricare(fileTornato.contenuto)
    //dispatch(setIsLoading(false));
   dispatch({type:'FINECARICAMENTO',payload:1})

    let nomeDelFile = 'pippo.zip'
    const blob = new Blob([fileTornato.contenuto],{type:"application/x-zip-compressed"+"/plain"})
    //fileDownload(response.data.contenuto, nomeDelFile)
    
}

function handleFile(e) {
    setFile(e)
    setNomeFile(e.name)
    setContent(e.type)
    
    
}



// const Table = (
//   <>
//     {fileDaScaricare &&
//       <table className="table ">
//         <thead>
//       <tr>
//         <th scope="col">Nomi dei file controllati</th>
//         </tr>
//         </thead>
//         <tbody className="table-group-divider">
          
//            { 
//             fileDaScaricare.map((file)  =>(
//               <tr>
            
//              <Link  target="_blank" download>{file.nomeFile}</Link>
//                </tr>
            
//             ))
            
//             }
//             </tbody>
//             </table>
// }
//           </>

//             )
          
        


    return(
        <>
        
        <div className="ILPADDING">
        <div className="card card-simple border-secondary">
    <div className="card-body px-0">
        <h3 className="card-title">Controlla Archivio</h3>
        <span className="text-muted">Inserisci file di tipo zip 7 zip o rar4</span>
        <p className="card-text">assicurati che contengano solo file docx excell jpg png o pdf</p>
        <div className="mb-3">
    <label htmlFor="ex-file" className="form-label">Cartella:</label>
    <input type="file" id="ex-file" name="ex-file" className="form-control" onChange={(e)=>handleFile(e.target.files[0])} />
</div>

<button type="button" className="btn btn-primary btn-sm" onClick={() => Main(file)} >Effetua controllo</button>
          {errore && <span className="child">{errore}</span>}
          {risposta && <span className="child">{risposta}</span>}
          
          
        <Link href="#">
            <Link className="bi bi-arrow-right me-2"></Link>Link con icona
   

        </Link>
    </div>
</div>
</div>
        </>
    )
    }
    

export default FileUpload