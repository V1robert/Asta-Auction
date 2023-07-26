package interfaces;

import java.util.List;


import javax.ejb.Local;

import models.Articolo;

@Local
public interface ArticoloInterface {

	public Articolo insert(Articolo articolo);

	public Articolo update(Articolo articolo);

	public void delete(Integer id);
	
	public Articolo findById(Integer id);
	
	public List<Articolo> findArticoliUtente(Articolo articolo);
	
	public  Articolo findArticoloByNome(Articolo articolo);
	
	public List<Articolo> findAll();
}

