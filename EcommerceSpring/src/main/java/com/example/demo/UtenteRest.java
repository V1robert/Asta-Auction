package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UtenteRest")
@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST})
public class UtenteRest {

	@Autowired
	UtenteService utenteService;
	
	@PostMapping("/insert")
	public ResponseEntity<Utente> insert(@RequestBody Utente utente) {
		Utente utenteInserito = utenteService.insert(utente);
		
		return ResponseEntity.ok(utenteInserito);
	}
	
	
	@PostMapping("/findByEmailAndPassword")
	public ResponseEntity<?> findByEmailAndPass(@RequestBody UtenteDtoLogin utenteInArrivo){
		Utente utente = utenteService.findByEmailAndPass(utenteInArrivo.getEmail(), utenteInArrivo.getPass());
		if(utente != null) {
		return ResponseEntity.ok(utente);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("errore");
		}
	}
}
