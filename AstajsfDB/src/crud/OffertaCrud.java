package crud;

import java.util.List;

import mapping.OffertaMapping;
import models.Offerta;

public class OffertaCrud extends BaseCrud<Offerta,OffertaMapping>{

	@Override
	public Offerta insert(Offerta offerta, OffertaMapping mapping) {
		try {
			mapping.insert(offerta);
			return offerta;
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
	public Offerta findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	@Override
	public List<Offerta> findAll(OffertaMapping mapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offerta update(Offerta model, OffertaMapping modeli) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Offerta trovaOffertaDelUtente(Offerta offerta,OffertaMapping mapper) {
		try {
			offerta=mapper.trovaOffertaDelUtente(offerta);
			return offerta;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
