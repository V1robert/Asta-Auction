package interfaces;

import javax.ejb.Local;

import models.Offerta;

@Local
public interface OffertaInterface {

	public Offerta insert(Offerta offerta);

	public Offerta update(Offerta offerta);

	public void delete(Integer id);
	
	public Offerta findById(Integer id);
	
	public Offerta trovaOffertaDelUtente(Offerta offerta);
}
