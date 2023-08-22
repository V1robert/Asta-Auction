package banca.interfaces;

import java.util.List;

import javax.ejb.Local;

import org.apache.ibatis.annotations.Param;

import banca.models.Conto;


@Local
public interface ContoInterface {

	public Conto inserisciConto(Conto conto);
	
	public List <Conto>findContoByCf(String cf);
	
	List<Conto> findContiByStato(Integer id);
	
    public void bloccaConto( Integer id);
	
	public void attivaConto( Integer id);
	
	public void modificaSaldo(Conto conto);
	

	
	

}
