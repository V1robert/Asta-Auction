package controller;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import crud.AstaCrud;
import interfaces.AstaInterface;
import mapping.AstaMapping;
import models.Asta;
import models.Utente;
import sqlfactory.SqlMapFactory;

@LocalBean
@Stateless(name = "AstaInterface")
public class AstaController implements AstaInterface {

	@Override
	public Asta insert(Asta asta) {
		System.out.println("insert Controller asta --> " + asta);
		try {
			SqlMapFactory.instance().openSession();
			AstaMapping mapper = SqlMapFactory.instance().getMapper(AstaMapping.class);
			AstaCrud astaCrud = new AstaCrud();
			asta = astaCrud.insert(asta, mapper);
			SqlMapFactory.instance().commitSession();
			return asta;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Asta update(Asta asta) {
		System.out.println("update Controller asta --> " + asta);
		try {
			SqlMapFactory.instance().openSession();
			AstaMapping mapper = SqlMapFactory.instance().getMapper(AstaMapping.class);
			AstaCrud astaCrud = new AstaCrud();
			asta = astaCrud.update(asta, mapper);
			SqlMapFactory.instance().commitSession();
			return asta;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Asta findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	//@Schedule(second= "", minute = "/1", hour = "*", persistent = false)
	public void controllaChiusuraAste() {
		System.out.println("chiusura aste --> ");
		try {
			SqlMapFactory.instance().openSession();
			AstaMapping mapper = SqlMapFactory.instance().getMapper(AstaMapping.class);
			AstaCrud astaCrud = new AstaCrud();
			List<Asta> aste = astaCrud.findAllAste(mapper);
			SqlMapFactory.instance().commitSession();

			// il compare to confronta 2 date , se sono uguali torna 0 , se la data 1 verra
			// dopo la data 2 torna piu di 0 , se la data 1 e minore della data 2 torna meno
			// di 0

			Date dataDiOggi = new Date();
			for (int x = 0; x < aste.size(); x++) {

				if (aste.get(x).getDataScadenza().compareTo(dataDiOggi) == 0) {
					System.out.println("l'asta scade oggi");
				} else if (aste.get(x).getDataScadenza().compareTo(dataDiOggi) > 0) {
					System.out.println("l'asta è ancora valida");

				} else if (aste.get(x).getDataScadenza().compareTo(dataDiOggi) < 0) {
					System.out.println("l'asta non è piu valida");
					astaFinita(aste.get(x));

					if (null == aste.get(x).getPrezzoVenduto() && null != aste.get(x).getIdOffertaPiuAlta()) {
						aste.get(x).setPrezzoVenduto(aste.get(x).getOfferta().getSoldiOfferti());
						Asta astaDaTerminare = aste.get(x);

						System.out.println(astaDaTerminare);
						astaFinita(astaDaTerminare);

					} else if (null == aste.get(x).getIdOffertaPiuAlta()) {
						System.out.println("nessuno ha mai fatto un offerta che superi il prezzo minimo di" + " "
								+ aste.get(x).getPrezzoPartenza());
						chiusuraAsta(aste.get(x));

					} else {
						System.out.println("l'offerta vincente è " + " " + aste.get(x).getOfferta().getSoldiOfferti());
					}

					aste.remove(x);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}
	

	@Override
	public List<Asta> findAllAste() {
		System.out.println("findAllLista aste --> ");
		try {
			SqlMapFactory.instance().openSession();
			AstaMapping mapper = SqlMapFactory.instance().getMapper(AstaMapping.class);
			AstaCrud astaCrud = new AstaCrud();
			List<Asta> aste = astaCrud.findAllAste(mapper);
			SqlMapFactory.instance().commitSession();

			return aste;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Asta astaFinita(Asta asta) {
		System.out.println("update Controller astaFinita --> " + asta);
		try {
			SqlMapFactory.instance().openSession();
			AstaMapping mapper = SqlMapFactory.instance().getMapper(AstaMapping.class);
			AstaCrud astaCrud = new AstaCrud();
			asta = astaCrud.astaFinita(asta, mapper);
			SqlMapFactory.instance().commitSession();
			return asta;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}
	
	public Asta chiusuraAsta(Asta asta) {
		System.out.println("update Controller astaChiusa --> " + asta);
		try {
			SqlMapFactory.instance().openSession();
			AstaMapping mapper = SqlMapFactory.instance().getMapper(AstaMapping.class);
			AstaCrud astaCrud = new AstaCrud();
			asta = astaCrud.chiusuraAsta(asta, mapper);
			SqlMapFactory.instance().commitSession();
			return asta;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public List<Asta> vediLeAsteVincentii(Asta astaa,Utente utenteLoggato) {
		System.out.println("findAllLista aste --> ");
		try {
			SqlMapFactory.instance().openSession();
			AstaMapping mapper = SqlMapFactory.instance().getMapper(AstaMapping.class);
			AstaCrud astaCrud = new AstaCrud();
			List<Asta> aste = astaCrud.vediLeAsteVincentii(mapper, utenteLoggato);
			SqlMapFactory.instance().commitSession();
			
			for (int x = 0; x < aste.size(); x++) {
				if (null == aste.get(x).getPrezzoVenduto()) {
					aste.remove(x);
				}
			}
			
			return aste;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

}
