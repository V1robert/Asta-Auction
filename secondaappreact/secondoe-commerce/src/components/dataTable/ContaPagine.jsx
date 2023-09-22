import React from "react";
import { useSelector } from "react-redux";
import { useState } from "react";

function ContaPagine() {

    const[paginaCorrente,setPaginaCorrente] =useState(1)
    const contaPagine = useSelector((state) => state.pagine)

    const indiceDelUltimoFile= contaPagine.indiceDelUltimoFile
    const indiceDelPrimoFile= contaPagine.indiceDelPrimoFile
    const fileCorrenti= contaPagine.fileCorrenti
    const fileTotali = contaPagine.fileTotali
    console.log(fileTotali)
    console.log(fileCorrenti)
    console.log(indiceDelPrimoFile)
    console.log(indiceDelUltimoFile)

    

const paginate = (contanumeroPagina) => {
    setPaginaCorrente(numeroPagina)
  }
  
  const Pagine = ({filePerPagina,fileTotali }) => {
    const numeroPagine = []
    
  
    for (let i = 1; i <= Math.ceil(fileTotali / filePerPagina); i++) {
      numeroPagine.push(i)
      
    }
  
    return (
          <>
        <nav aria-label="Paginazione ...">
      <ul className="pagination justify-content-center">
          {numeroPagine.map((index) =>(
      <li key={index} className="page-item active" onClick={() => paginate(index)} aria-current="page">
           <span className="page-link">
               <span className="visually-hidden">Pagina </span>{index}
           </span>
       </li> 
          ))}  
       </ul>
       </nav>
       </>
    )
  }
}

export default ContaPagine