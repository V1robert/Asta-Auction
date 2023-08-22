package crud;

import java.util.List;

import mapping.AstaMapping;
import models.Asta;
import models.Utente;

public class AstaCrud extends BaseCrud<Asta,AstaMapping> {

	@Override
	public Asta insert(Asta asta, AstaMapping mapper) {
		try {
			mapper.insert(asta);
			return asta;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Asta update(Asta asta,AstaMapping mapper) {
		try {
			mapper.update(asta);
			return asta;
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
	public Asta findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Asta> findAllAste(AstaMapping mapper) {
		try {
			List<Asta> aste=mapper.findAllAste();
			return aste;
		}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		
	}

	public Asta astaFinita(Asta asta,AstaMapping mapper) {
		try {
			mapper.astaFinita(asta);
			return asta;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Asta chiusuraAsta(Asta asta,AstaMapping mapper) {
		try {
			mapper.astaFinita(asta);
			return asta;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<Asta> findAll(AstaMapping mapper) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Asta> vediLeAsteVincentii(AstaMapping mapper,Utente utenteLoggato){
		try {
			List<Asta>aste=mapper.vediLeAsteVincentii(utenteLoggato);
			return aste;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
