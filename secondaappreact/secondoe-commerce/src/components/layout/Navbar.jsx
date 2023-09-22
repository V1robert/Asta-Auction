import React from "react";
import { Link } from "react-router-dom";
function Navbar(){

    return(
        <>
        <nav className="navbar navbar-expand-md navbar-light" aria-label="Menu principale">
    <h1 className="visually-hidden">Menu principale</h1>
    <div className="container">
        <div className="navbar-collapse collapse" id="navbar-menu">
            <ul className="navbar-nav me-auto">
                <li className="nav-item">
                    <a className="nav-link" href="#">Home</a>
                </li>
                <li className="nav-item">
                    <Link className="nav-link active" to={'/Upload'} href="#">ControllaZip</Link>
                </li>
                <li className="nav-item dropdown">
                    <a className="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">Registrati o Login</a>
                    <ul className="dropdown-menu">
                        <li>
                            <Link className="dropdown-item" to={'/Form'} href="#">Registrati</Link>
                        </li>
                        <li>
                            <a className="dropdown-item" href="#">Login</a>
                        </li>
                    </ul>
                </li>
                <li className="nav-item megamenu">
                    <a className="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">Esplora risorse</a>
                    <div className="dropdown-menu shadow">
                        <div className="container">
                            <div className="row">
                                <div className="col-12 col-sm-6 col-lg-4">
                                    <span className="megamenu-title">Titolo 1</span>
                                   
                                    <ul>
                                        <li>
                                            <a className="megamenu-item" href="#">Voce 1.1</a>
                                        </li>
                                        <li>
                                            <a className="megamenu-item" href="#">Voce 1.2</a>
                                        </li>
                                    </ul>
                                </div>
                                <div className="col-12 col-sm-6 col-lg-4">
                                    <span className="megamenu-title">Titolo 2</span>
                                    <ul>
                                        <li>
                                            <a className="megamenu-item" href="#">Voce 2.1</a>
                                        </li>
                                        <li>
                                            <a className="megamenu-item" href="#">Voce 2.2</a>
                                        </li>
                                    </ul>
                                </div>
                                <div className="col-12 col-sm-6 col-lg-4">
                                    <span className="megamenu-title">Titolo 3</span>
                                    <ul>
                                        <li>
                                            <a className="megamenu-item" href="#">Voce 3.1</a>
                                        </li>
                                        <li>
                                            <a className="megamenu-item" href="#">Voce 3.2</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-12 col-lg-8">
                                    <a className="megamenu-title" href="#">Titolo 4 con link</a>
                                    <div className="row">
                                        <div className="col-12 col-sm-6">
                                            <ul>
                                                <li>
                                                    <a className="megamenu-item" href="#">Voce 4.1</a>
                                                </li>
                                                <li>
                                                    <a className="megamenu-item" href="#">Voce 4.2</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div className="col-12 col-sm-6">
                                            <ul>
                                                <li>
                                                    <a className="megamenu-item" href="#">Voce 4.3</a>
                                                </li>
                                                <li>
                                                    <a className="megamenu-item" href="#">Voce 4.4</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-12 col-sm-6 col-lg-4">
                                    <span className="megamenu-title">Titolo 5</span>
                                    <ul>
                                        <li>
                                            <a className="megamenu-item" href="#">Voce 5.1</a>
                                        </li>
                                        <li>
                                            <a className="megamenu-item" href="#">Voce 5.2</a>
                                        
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li className="nav-item">
                    <a className="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Admin</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

        </>
    )
}

export default Navbar