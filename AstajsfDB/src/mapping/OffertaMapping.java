package mapping;

import models.Offerta;

public interface OffertaMapping {

	public void insert(Offerta Offerta);

	public void update(Offerta Offerta);

	public void delete(Integer id);
	
	public Offerta findById(Integer id);
	
	public Offerta trovaOffertaDelUtente(Offerta offerta);
}
