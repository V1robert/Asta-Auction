package interfaces;

import java.util.List;

import javax.ejb.Local;

import models.Asta;
import models.Utente;

@Local
public interface AstaInterface {


	public Asta insert(Asta asta);

	public Asta update(Asta asta);

	public void delete(Integer id);
	
	public Asta findById(Integer id);
	
	public List<Asta> findAllAste();
	
	public Asta astaFinita (Asta asta);

	public List<Asta> vediLeAsteVincentii(Asta astaa,Utente utenteLoggato);

	
}
