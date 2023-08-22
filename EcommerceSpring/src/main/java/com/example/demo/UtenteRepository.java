package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//le repository devono avere @repository oppure estendere JpaRepository oppure altre interfacce repository

public interface UtenteRepository extends CrudRepository<Utente,Long> {

	public Utente findByEmailAndPass(@Param(value="EMAIL")String email,@Param(value="PASSWORD")String password);
	
}
