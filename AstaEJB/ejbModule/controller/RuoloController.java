package controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import crud.RuoloCrud;
import interfaces.RuoloInterface;
import models.Ruolo;


@LocalBean
@Stateless(name="RuoloInterface")
public class RuoloController implements RuoloInterface {

	@Override
	public Ruolo insert(Ruolo ruolo) {
		System.out.println("insert Controller ruolo --> " + ruolo);
		RuoloCrud ruoloCrud = new RuoloCrud();
		//ruolo = ruoloCrud.insert(ruolo);
		return ruolo;
	}

}
