package crud;

import java.util.List;

import mapping.ArticoloMapping;
import models.Articolo;

public class ArticoloCrud extends BaseCrud<Articolo,ArticoloMapping>{

	@Override
	public Articolo insert(Articolo articolo, ArticoloMapping mapper) {
		try {
			mapper.insert(articolo);
			return articolo;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public byte[] inserisciFoto(byte[] file,ArticoloMapping mapper) {
		try {
			mapper.inserisciFoto(file);
			return file;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Articolo findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	public List<Articolo> findArticoliUtente(Articolo articolo,ArticoloMapping mapper) {
		try {
			List<Articolo>articoli=mapper.findArticoloUtente(articolo);
			return articoli;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Articolo findArticoloByNome(Articolo articolo,ArticoloMapping mapper) {
		try {
			articolo=mapper.findArticoloByNome(articolo);
			return articolo;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<Articolo> findAll(ArticoloMapping mapper) {
		try {
			List<Articolo>articoli=mapper.findAll();
			return articoli;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}




	@Override
	public Articolo update(Articolo model, ArticoloMapping modeli) {
		// TODO Auto-generated method stub
		return null;
	}

}
