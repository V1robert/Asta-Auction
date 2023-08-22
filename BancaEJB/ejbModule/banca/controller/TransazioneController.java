package banca.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import banca.crud.ContoCrud;
import banca.crud.TransazioneCrud;

import banca.interfaces.TransazioneInterface;

import banca.models.Transazione;

@Stateless(name = "TransazioneInterface")
@LocalBean
public class TransazioneController implements TransazioneInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7392073499071750839L;

	@Override
	public List<Transazione> findByStatoTransazione(Integer idStato) {
		TransazioneCrud transazioneCrud = new TransazioneCrud();
		List<Transazione> listaTransazioni = transazioneCrud.findByStatoTransazione(idStato);
		return listaTransazioni;
	}

	@Override
	public List<Transazione> findByConto(Integer conto) {
		TransazioneCrud transazioneCrud = new TransazioneCrud();
		List<Transazione> listaTransazioni = transazioneCrud.findByConto(conto);
		return listaTransazioni;
	}

	@Override
	public Transazione insertTransazione(Transazione transazione) {
		TransazioneCrud transazioneCrud = new TransazioneCrud();
		Transazione transazioneInserita = transazioneCrud.insertTransazione(transazione);
		return transazioneInserita;

	}

	@Override
	public void rifiutaTransazione(Transazione transazione) {
		TransazioneCrud transazioneCrud = new TransazioneCrud();
		transazioneCrud.rifiutaTransazione(transazione);

	}

	@Override
	public void accettaTransazione(Transazione transazione) {
		TransazioneCrud transazioneCrud=new TransazioneCrud();
		ContoCrud contoCrud=new ContoCrud();
		if(transazione.getTipoTransazione().getId()==1) {
			if(transazione.getConto().getSaldo()>=transazione.getImporto()) {
				transazione.getConto().setSaldo(transazione.getConto().getSaldo()-transazione.getImporto());
				contoCrud.modificaSaldo(transazione.getConto());
				transazioneCrud.accettaTransazione(transazione);
			}
		}else if(transazione.getTipoTransazione().getId()==2) {
			transazione.getConto().setSaldo(transazione.getConto().getSaldo()+transazione.getImporto());
			contoCrud.modificaSaldo(transazione.getConto());
			transazioneCrud.accettaTransazione(transazione);
		}

	}
}
