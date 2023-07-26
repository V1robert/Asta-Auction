package mapping;

import java.util.List;

import models.Articolo;

public interface ArticoloMapping {

	
	public void insert(Articolo articolo);

	public void update(Articolo articolo);

	public void delete(Integer id);
	
	public Articolo findById(Integer id);
	
	public List<Articolo> findArticoloUtente(Articolo articolo);
	
	public Articolo findArticoloByNome(Articolo articolo);
	
	public List<Articolo> findAll();
	
}
