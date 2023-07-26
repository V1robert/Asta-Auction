package crud;

import java.util.List;

import mapping.UserMapping;
import models.Utente;

public class UtenteCrud extends BaseCrud<Utente,UserMapping>{

	public Utente insert(Utente utente,UserMapping mapper) {
		try {
		mapper.insert(utente);
		return utente;
		}catch(Exception e) {
			e.printStackTrace();
		}
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

	
	
	public Utente login(Utente utente,UserMapping mapper) {
		try {
		utente=mapper.findByEmailPass(utente);
	

		return utente;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Utente> findAll(UserMapping mapper) {
		try {
			List<Utente>utenti=mapper.findAll();
			return utenti;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public Utente update(Utente model, UserMapping modeli) {
		// TODO Auto-generated method stub
		return null;
	}

}
