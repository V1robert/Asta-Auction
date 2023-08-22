package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface RuoloRepository extends CrudRepository<Ruolo,Long> {

	public Ruolo findById(Integer id);
}
