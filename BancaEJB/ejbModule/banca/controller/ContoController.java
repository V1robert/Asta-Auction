package banca.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import banca.crud.ContoCrud;
import banca.interfaces.ContoInterface;
import banca.models.Conto;


@Stateless(name = "ContoInterface")
@LocalBean
public class ContoController implements Serializable, ContoInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2279651207255118978L;

	@Override
	public Conto inserisciConto(Conto conto) {
		ContoCrud contoCrud = new ContoCrud();
		Conto contoInserito = contoCrud.inserisciConto(conto);
		return contoInserito;

	}

	@Override
	public List<Conto> findContoByCf(String cf) {
		ContoCrud contoCrud = new ContoCrud();
		List<Conto> listaConti = contoCrud.findContoByCf(cf);
		return listaConti;
	}
	
	@Override
	public List<Conto> findContiByStato(Integer id) {
		ContoCrud contoCrud = new ContoCrud();
		List<Conto> listaConti = contoCrud.findContiByStato(id);
		return listaConti;
	}

	@Override
	public void bloccaConto(Integer id) {
		ContoCrud contoCrud = new ContoCrud();
		contoCrud.bloccaConto(id);
	}

	@Override
	public void attivaConto(Integer id) {
		ContoCrud contoCrud = new ContoCrud();
		contoCrud.attivaConto(id);		
	}

	@Override
	public void modificaSaldo(Conto conto) {
		ContoCrud contoCrud = new ContoCrud();
		 contoCrud.modificaSaldo(conto);
	
	}



}
