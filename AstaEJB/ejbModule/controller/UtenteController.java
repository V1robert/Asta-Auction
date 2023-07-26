package controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import crud.UtenteCrud;
import interfaces.UtenteInterface;
import mapping.UserMapping;
import models.Ruolo;
import models.Utente;
import sqlfactory.SqlMapFactory;
@LocalBean
@Stateless(name="UtenteInterface")
public class UtenteController implements UtenteInterface {

	@Override
	public Utente insert(Utente utente) {
		System.out.println("insert Controller utente --> " + utente);
		try {
			SqlMapFactory.instance().openSession();
			UserMapping mapper= SqlMapFactory.instance().getMapper(UserMapping.class);
			UtenteCrud utenteCrud = new UtenteCrud();
			utente=utenteCrud.insert(utente,mapper );
			SqlMapFactory.instance().commitSession();
			return utente;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Utente update(Utente Utente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utente findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> findAll() {
		System.out.println("findAll di utenti controller --> " );
		try {
			SqlMapFactory.instance().openSession();
			UserMapping mapper= SqlMapFactory.instance().getMapper(UserMapping.class);
			UtenteCrud utenteCrud = new UtenteCrud();
			List<Utente>utenti=utenteCrud.findAll(mapper);
			SqlMapFactory.instance().commitSession();
			return utenti;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public List<Ruolo> findAllRuoli() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente insertAuto(Utente Utente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente findByEmailPass(Utente utente) {
		System.out.println("find by email and pass utente --> " + utente);
		try {
			SqlMapFactory.instance().openSession();
			UserMapping mapper= SqlMapFactory.instance().getMapper(UserMapping.class);
			UtenteCrud utenteCrud = new UtenteCrud();
			utente=utenteCrud.login(utente,mapper);
			SqlMapFactory.instance().commitSession();
			return utente;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Utente updateEmailAndPassword(Utente Utente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente updateUtenteAdmin(Utente Utente) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
