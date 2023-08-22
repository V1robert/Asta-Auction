package banca.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import banca.crud.UtenteCrud;
import banca.interfaces.UtenteInterface;
import banca.models.Utente;

@Stateless(name = "UtenteInterface")
@LocalBean
public class UtenteController implements UtenteInterface, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Utente registrazioneUtente(Utente utente) {
		System.out.println("sono nel controller pre");
		UtenteCrud utenteCrud = new UtenteCrud();
		Utente utenteInserito = utenteCrud.registrazioneUtente(utente);
		System.out.println("sono nel controller post");
		return utenteInserito;

	}

	@Override
	public Utente findByMailAndPwd(String email, String pwd) {
		UtenteCrud utenteCrud = new UtenteCrud();
		Utente utenteDaTrovare = utenteCrud.findByMailAndPwd(email, pwd);
		if (utenteDaTrovare != null) {
			return utenteDaTrovare;
		} else {
			return null;
		}

	}

	@Override
	public List<Utente> findAllUtenti() {
		UtenteCrud utenteCrud = new UtenteCrud();
		List<Utente> listaUtenti = utenteCrud.findAllUtenti();
		return listaUtenti;

	}
	@Override
	public List<Utente> findAllStaff() {
		UtenteCrud utenteCrud = new UtenteCrud();
		List<Utente> listaUtenti = utenteCrud.findAllStaff();
		return listaUtenti;
		
	}

	@Override
	public Utente findByCf(String cf) {
		UtenteCrud utenteCrud = new UtenteCrud();
		Utente utenteDaTrovare = utenteCrud.findByCf(cf);
		if (utenteDaTrovare != null) {
			return utenteDaTrovare;
		} else {
			return null;
		}
	}

	@Override
	public Utente updateUtente(Utente utente) {

		UtenteCrud utenteCrud = new UtenteCrud();
		utenteCrud.updateUtente(utente);
		utente = utenteCrud.findById(utente.getId());
		return utente;
		
	}

	@Override
	public Utente updateUtenteByAdmin(Utente utente) {
		UtenteCrud utenteCrud = new UtenteCrud();
		utenteCrud.updateUtenteByAdmin(utente);
		utente = utenteCrud.findByCf(utente.getCf());
		return utente;
	}

	@Override
	public Utente findById(Integer id) {
		UtenteCrud utenteCrud = new UtenteCrud();
		Utente utenteDaTrovare = utenteCrud.findById(id);
		if (utenteDaTrovare != null) {
			return utenteDaTrovare;
		} else {
			return null;
		}
	}
	

}
