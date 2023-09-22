import { Outlet } from "react-router";
import logoADMit from "../../designSystem/img/ADM_ita-bianco.svg"
import React from "react";
function Header() {
    return (
        
        <>
        <header aria-label="Funzioni di servizio">
            <div className="header-top-dark">
                <div className="container py-2">
                    <div className="row gx-variable align-items-center">
                        <div className="col d-none d-md-block">
                            <a href="#" className="link-white fw-semibold">Ente appartenenza: Robert</a>
                        </div>
                        <div className="col col-md-auto text-end">
                            <div className="dropdown">
                                <button type="button" id="language" className="btn btn-sm link-white dropdown-toggle"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <span className="visually-hidden">Lingue disponibili:</span>ITA
                                </button>
                                <ul className="dropdown-menu" aria-labelledby="language">
                                    <li>
                                        <a className="dropdown-item" href="#">ITA<span className="visually-hidden">: italiano</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a className="dropdown-item" href="#" lang="en">ENG<span
                                            className="visually-hidden">: english</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="header-dark">
                <div className="container py-3 py-md-4">
                    <div className="row gx-variable align-items-center">
                        <div className="col-auto d-block d-md-none">
                            <button type="button" className="navbar-toggler" data-bs-toggle="collapse"
                                    data-bs-target="#navbar-menu" aria-controls="navbar-menu" aria-expanded="false">
                                <span className="visually-hidden">Apri menu principale</span>
                                <i className="bi bi-list bi-2x"></i>
                            </button>
                        </div>
                        <div className="col-3">
                            <img src={logoADMit} alt="" className="logo"/>
                        </div>
                        <div className="col">
                            <h1>Controllo archivio<small>Sito di controlli</small>
                            </h1>
                        </div>
                        <div className="col-auto d-none d-md-block text-end">
                            <span className="me-3">Seguici su:</span>
                            <ul className="list-inline d-inline-block">
                                <li className="list-inline-item">
                                    <a href="#" target="_blank" className="link-white me-2" title="Facebook">
                                        <i className="bi bi-facebook bi-lg"></i>
                                        <span className="visually-hidden">Facebook: apre una nuova finestra</span>
                                    </a>
                                </li>
                                <li className="list-inline-item">
                                    <a href="#" target="_blank" className="link-white me-2" title="Twitter">
                                        <i className="bi bi-twitter bi-lg"></i>
                                        <span className="visually-hidden">Twitter: apre una nuova finestra</span>
                                        
                                    </a>
                                </li>
                                <li className="list-inline-item">
                                    <a href="#" target="_blank" className="link-white me-2" title="Instagram">
                                        <i className="bi bi-instagram bi-lg"></i>
                                        <span className="visually-hidden">Instagram: apre una nuova finestra</span>
                                    </a>
                                </li>
                                <li className="list-inline-item">
                                    <a href="#" target="_blank" className="link-white me-2" title="YouTube">
                                        <i className="bi bi-youtube bi-lg"></i>
                                        <span className="visually-hidden">YouTube: apre una nuova finestra</span>
                                    </a>
                                </li>
                            </ul>
                            <form name="" action="" className="navbar-form">
                                <label htmlFor="search" className="visually-hidden">Cerca nel sito:</label>
                                <div className="input-group">
                                    <input type="text" id="search" name="search" className="form-control border-white"
                                           placeholder="Cerca..."/>
                                    <button type="submit" className="btn btn-outline-white">
                                        <i className="bi bi-search"></i>
                                        <span className="visually-hidden">Cerca</span>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        
        </>
    );
}

export default Header;