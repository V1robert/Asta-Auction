package mapping;

import java.util.List;

import models.Asta;
import models.Utente;

public interface AstaMapping {


	public void insert(Asta asta);

	public void update(Asta asta);

	public void delete(Integer id);
	
	public Asta findById(Integer id);
	
	public List<Asta> findAllAste();
	
	public void astaFinita(Asta asta);
	
	public List<Asta> vediLeAsteVincentii(Utente utenteLoggato);
	
	public Asta chiusuraAsta(Asta asta); 
}
