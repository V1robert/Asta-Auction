package interfaces;

import javax.ejb.Local;

import models.Asta;
import models.Offerta;
import models.Utente;

@Local
public interface OffertaInterface {

	public Offerta insert(Offerta offerta);

	public Offerta update(Offerta offerta);

	public void delete(Integer id);
	
	public Offerta findById(Integer id);
	
	public Offerta trovaOffertaDelUtente(Offerta offerta);
	
	public Asta inserimentoOfferta(Offerta offerta,Asta astaInSessione,Utente utenteLoggato) throws Exception;
}
