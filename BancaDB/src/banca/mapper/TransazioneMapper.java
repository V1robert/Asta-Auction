package banca.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import banca.models.Transazione;

public interface TransazioneMapper {

	List<Transazione> findByStatoTransazione(@Param("idStato") Integer idStato);

	List<Transazione> findByConto(@Param("nConto") Integer nConto);

	void insertTransazione(Transazione transazione);

	void rifiutaTransazione( @Param("id")Integer id);
	
	void accettaTransazione( @Param("id")Integer id);

}
