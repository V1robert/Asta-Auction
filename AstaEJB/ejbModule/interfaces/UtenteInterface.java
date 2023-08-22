package interfaces;

import java.util.List;

import javax.ejb.Local;

import models.Ruolo;
import models.Utente;

@Local
public interface UtenteInterface {

	public Utente insert(Utente Utente);

	public Utente update(Utente Utente);

	public void delete(Integer id);
	
	public Utente findById(Integer id);

	public List<Utente> findAll();
	
	public List<Ruolo> findAllRuoli();

	public Utente insertAuto(Utente Utente);

	public Utente findByEmailPass(Utente Utente);
	
    public Utente updateEmailAndPassword(Utente Utente);
    
    public Utente updateUtenteAdmin(Utente Utente);
    
    public Utente loggate(Utente utenteLoggato,Utente utente) throws Exception;
		
	public Boolean controllaRuolo(Utente utenteLoggato);
	
	public void pagaAsta(Utente utente,Integer prezzoVenduto,String codiceFiscale);
	
	public Utente loginSpring(Utente utenteLoggato,Utente utente) throws Exception;
}
