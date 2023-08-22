package banca.interfaces;

import java.util.List;

import javax.ejb.Local;

import banca.models.Transazione;

@Local
public interface TransazioneInterface {

	List<Transazione> findByStatoTransazione(Integer statoTransazione);

	List<Transazione> findByConto(Integer conto);

	Transazione insertTransazione(Transazione transazione);

	void rifiutaTransazione(Transazione transazione);
	
	void accettaTransazione(Transazione transazione);
}
