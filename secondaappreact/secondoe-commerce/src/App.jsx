import React from 'react';
import './designSystem/css/adm.css'
import './designSystem/js/bootstrap.bundle.min'
import './App.css';
import { Outlet, Route, Routes } from 'react-router';
import Navbar from './components/layout/Navbar';
import Header from './components/layout/Header';
import FileUpload from './components/dataTable/FileUpload';
import Footer from './components/layout/Footer';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import PaginaErrore from './components/utils/PaginaErrore'
import Form from './components/loginRegister/Form';
import { Modal } from 'react-bootstrap';
import SpinnerModal from './components/utils/SpinnerModal';
import { useSelector } from 'react-redux';

const router = createBrowserRouter([

  

  {
    path: "/",
    element: (
    <>
    <Header/>
    <Navbar/>
    <Outlet/>
    <Footer/>
    </>  
    
    ),
    errorElement:<PaginaErrore/>,
    children:[
      { path:"Upload",element: <FileUpload /> },
      {
        path:"Form",
        element: <Form/>
      },

    ],
  }, 
    
  
]);

function App() {

  const loading = useSelector((state)=> state.spinner)
   
  return(
    <>
     <RouterProvider router={router} />
     <Modal show={loading.isLoading} backdrop="static" dialogAs={() => <SpinnerModal/>}/>  
     </>
  ) 
  
  
}

export default App;
