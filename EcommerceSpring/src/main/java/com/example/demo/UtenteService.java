package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

	@Autowired
	UtenteRepository utenteRepository;
	
	public Utente findByEmailAndPass(String email,String password) {
		
		return utenteRepository.findByEmailAndPass(email, password);
	
	}
	
	// il save e di base crud di spring , salva l'entita
	public Utente insert(Utente utente) {
		return utenteRepository.save(utente);
	}
}
