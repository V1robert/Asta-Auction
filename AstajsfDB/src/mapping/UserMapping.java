package mapping;

import java.util.List;

import models.Ruolo;
import models.Utente;


public interface UserMapping {

	public void insert(Utente Utente);

	public void update(Utente Utente);

	public void delete(Integer id);
	
	public Utente findById(Integer id);

	public List<Utente> findAll();
	
	public List<Ruolo> findAllRuoli();
	
	
	
	public Utente findByEmailPass(Utente Utente);
	
	public void updateEmailAndPassword(Utente Utente);
	
	public void updateUtenteAdmin(Utente Utente);
}
