package banca.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import banca.models.Conto;


public interface ContoMapper {

	public void inserisciConto(Conto conto);
		
	public List <Conto> findContoByCf (@Param("cf") String cf);

	public void modificaSaldo(@Param("saldo")Double saldo, @Param("id")Integer nConto);
	
	
	public List<Conto> findContiByStato(@Param("id") Integer id);
	
	public void bloccaConto(@Param("id") Integer id);
	
	public void attivaConto(@Param("id") Integer id);

	
	
}
