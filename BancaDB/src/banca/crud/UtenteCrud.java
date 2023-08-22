package banca.crud;

import java.util.List;

import banca.mapper.UtenteMapper;
import banca.models.Utente;
import banca.mybatis.SqlMapFactory;

public class UtenteCrud {

	public Utente findByMailAndPwd(String mail, String pwd) {
		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			Utente utente = mapper.findByMailAndPwd(mail, pwd);
			SqlMapFactory.instance().commitSession();
			return utente;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public Utente registrazioneUtente(Utente utente) {
		try {
			System.out.println("sono pre crud");
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			utente.setCf(utente.getCf().toUpperCase());
			mapper.registrazioneUtente(utente);
			SqlMapFactory.instance().commitSession();
			System.out.println("sono post crud");
			return utente;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public List<Utente> findAllUtenti() {
		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			List<Utente> listaUtenti = mapper.findAllUtenti();
			SqlMapFactory.instance().commitSession();
			return listaUtenti;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}
	public List<Utente> findAllStaff() {
		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			List<Utente> listaUtenti = mapper.findAllStaff();
			SqlMapFactory.instance().commitSession();
			return listaUtenti;
		}
		
		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
			
		}
		
		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public Utente findByCf(String cf) {
		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			
			Utente utenteDaTrovare = mapper.findByCf(cf);
			utenteDaTrovare.setCf(utenteDaTrovare.getCf().toUpperCase());
			SqlMapFactory.instance().commitSession();
			return utenteDaTrovare;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public Utente findById(Integer id) {
		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			Utente utenteDaTrovare = mapper.findById(id);
			SqlMapFactory.instance().commitSession();
			return utenteDaTrovare;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public Utente updateUtente(Utente utente) {
		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			mapper.updateUtente(utente);
			utente = mapper.findById(utente.getId());
			SqlMapFactory.instance().commitSession();
			return utente;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public Utente updateUtenteByAdmin(Utente utente) {
		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			mapper.updateUtenteByAdmin(utente);
			utente = mapper.findByCf(utente.getCf());
			SqlMapFactory.instance().commitSession();
			return utente;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}
}
