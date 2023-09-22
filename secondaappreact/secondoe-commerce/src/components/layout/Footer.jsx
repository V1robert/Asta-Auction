import React from "react";



function Footer(){

    return(
        <>
       <footer>
    <div className="footer-dark">
        <div className="container py-3 py-md-4">
            <div className="row gx-variable align-items-center">
                <div className="col-auto">
                    <img src="" alt="" className="logo" />
                </div>
                <div className="col">
                    <h1>
                        <span className="visually-hidden">Informazioni su </span>Nome sito
                    </h1>
                </div>
            </div>
            <div className="row gx-variable">
                <div className="col-12 col-lg-4">
                    <hr />
                    <a href="#" className="link-white">Amministrazione trasparente</a>
                    <hr />
                </div>
            </div>
            <div className="row gx-variable">
                <div className="col-12 col-lg-8">
                    <h2>Recapiti e contatti</h2>
                    <hr />
                    <div className="row">
                        <div className="col-12 col-sm">
                            <p>Recapiti:
                                <br />Indirizzo
                            </p>
                        </div>
                        <div className="col-12 col-sm">
                            <p>Contatti:
                                <br />
                                <a href="mailto:" className="link-white">mail@mail.it</a>
                            </p>
                        </div>
                    </div>
                </div>
                <div className="col-12 col-lg-4">
                    <h2>Seguici su</h2>
                    <hr />
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
                </div>
            </div>
            <div className="row gx-variable">
                <div className="col-12">
                    <hr />
                    <h2 className="visually-hidden">Altre informazioni</h2>
                    <ul className="list-inline d-inline-block me-3">
                        <li className="list-inline-item">
                            <a href="#" className="link-white">Link utili</a>
                        </li>
                        <li className="list-inline-item">
                            <a href="#" className="link-white external-link" target="_blank">Dichiarazione di accessibilità<span className="visually-hidden">: apre una nuova finestra</span>
                            </a>
                        </li>
                        <li className="list-inline-item">
                            <a href="#" className="link-white">Note legali</a>
                        </li>
                        <li className="list-inline-item">
                            <a href="#" className="link-white">Privacy</a>
                        </li>
                        <li className="list-inline-item">
                            <a href="#" className="link-white">Cookie</a>
                        </li>
                    </ul>
                    <span className="fw-semibold">Ente appartenenza / Owner</span>
                </div>
            </div>
        </div>
    </div>
</footer>
        </>
    )
}

export default Footer