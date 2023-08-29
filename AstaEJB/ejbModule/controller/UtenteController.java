package controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import clientUtils.ServiceDelClient;
import costanti.CostantiUtenteController;
import crud.UtenteCrud;
import dto.UtenteDto;
import dto.UtenteDtoLogin;
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
			ServiceDelClient login = new ServiceDelClient();
			utente = login.loginSpring(creaDtoLogin(utente));
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
	public Utente loggate(Utente utenteLoggato,Utente utente) throws Exception{
		try {
			System.out.println(utente.getEmail());

			utenteLoggato = findByEmailPass(utente);
			System.out.println("sei loggato come" + utenteLoggato.toString());
			
			return utenteLoggato;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new Exception();
	}
	@Override
	public Utente loginSpring(Utente utenteLoggato,Utente utente) throws Exception{
		try {
			System.out.println(utente.getEmail());

			System.out.println(utente+"da manda");
			ServiceDelClient spring = new ServiceDelClient();
			utenteLoggato=spring.loginSpring(creaDtoLogin(utente));
			System.out.println("sei loggato come" + utenteLoggato.toString());
		
			return utenteLoggato;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new Exception();
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

	@Override
	public Boolean controllaRuolo(Utente utenteLoggato) {
		if(utenteLoggato.getIdRuolo()==CostantiUtenteController.RUOLO_UTENTE_ADMIN) {
			return true;
		}else {
			
			//l'utente non è un admin
			return false;
		}
		
	}
	
	public void pagaAsta(Utente utente,Integer prezzoVenduto,String codiceFiscale) {
		System.out.println("Paga asta utente --> " + utente);
		try {
			ServiceDelClient service = new ServiceDelClient();
			service.pagaAsta(creaDtoAsta(utente,codiceFiscale,prezzoVenduto));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	public UtenteDtoLogin creaDtoLogin(Utente utente) {
		UtenteDtoLogin credenziali = new UtenteDtoLogin();
		credenziali.setEmail(utente.getEmail());
		credenziali.setPass(utente.getPass());
		return credenziali;

	}
	public UtenteDto creaDtoAsta(Utente utenteLoggato, String cf, Integer prezzoVenduto) {
		UtenteDto utente = new UtenteDto();
		utente.setEmail(utenteLoggato.getEmail());
		utente.setPass(utenteLoggato.getPass());
		utente.setImportoDaPagare(prezzoVenduto);
		utente.setCodiceFiscale(cf);
		return utente;
	}
	
	

	
	
}
