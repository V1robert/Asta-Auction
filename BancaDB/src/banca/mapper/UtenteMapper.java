package banca.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import banca.models.Utente;



public interface UtenteMapper {

	Utente findByMailAndPwd (@Param("mail")String mail, @Param("pwd")String pwd);
	
	void registrazioneUtente (Utente utente);
	
	Utente findByCf(@Param("cf")String cf);
	
	void updateUtente (Utente utente);
	
	void updateUtenteByAdmin(Utente utente);
	
	Utente findById(@Param("id")Integer id);
	
	List <Utente> findAllUtenti();
	
	List <Utente> findAllStaff();

	
}
