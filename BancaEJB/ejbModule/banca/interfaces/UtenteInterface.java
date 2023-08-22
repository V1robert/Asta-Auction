package banca.interfaces;



import java.util.List;

import javax.ejb.Local;

import org.apache.ibatis.annotations.Param;

import banca.models.Utente;

@Local
public interface UtenteInterface {

	Utente registrazioneUtente(Utente utente);
	
	Utente findByMailAndPwd(String email, String pwd);
	
	List<Utente>findAllUtenti();
	
	List<Utente>findAllStaff();
	
	Utente findByCf(String cf);
	
	Utente updateUtente(Utente utente);
	
	Utente updateUtenteByAdmin(Utente utente);
	
	Utente findById(Integer id);

}