package controller;

import interfaces.OffertaInterface;
import mapping.OffertaMapping;
import models.Asta;
import models.Offerta;
import models.Utente;
import sqlfactory.SqlMapFactory;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import crud.OffertaCrud;

@LocalBean
@Stateless(name="OffertaInterface")
public class OffertaController implements OffertaInterface{

	@Override
	public Offerta insert(Offerta offerta) {
		System.out.println("insert Controller offerta --> " + offerta);
		try {
			SqlMapFactory.instance().openSession();
			OffertaMapping mapper = SqlMapFactory.instance().getMapper(OffertaMapping.class);
			OffertaCrud offertaCrud = new OffertaCrud();
			
			
			//setto la data di oggi come data dell offerta
			Date dataDiOggi=new Date();
			offerta.setDataInizio(dataDiOggi);
			
			//setto lo stato dell offerta in attesa
			offerta.setIdStatoOfferta(1);
			offerta = offertaCrud.insert(offerta, mapper);
			SqlMapFactory.instance().commitSession();
			return offerta;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Offerta update(Offerta offerta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Offerta findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offerta trovaOffertaDelUtente(Offerta offerta) {
		System.out.println("select Controller offertadelutente --> " + offerta);
		try {
			SqlMapFactory.instance().openSession();
			OffertaMapping mapper = SqlMapFactory.instance().getMapper(OffertaMapping.class);
			OffertaCrud offertaCrud = new OffertaCrud();
			offerta = offertaCrud.trovaOffertaDelUtente(offerta, mapper);
			SqlMapFactory.instance().commitSession();
			return offerta;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Asta inserimentoOfferta(Offerta offerta,Asta astaInSessione,Utente utenteLoggato) throws Exception {
 offerta.setIdUtente(utenteLoggato.getIdUtente());
		try {
			if (null == astaInSessione.getOfferta() 
					|| astaInSessione.getOfferta().getSoldiOfferti() < offerta.getSoldiOfferti()
							&&astaInSessione.getPrezzoPartenza()<=offerta.getSoldiOfferti()) {
				
				insert(offerta);
				
				offerta = trovaOffertaDelUtente(offerta);
				astaInSessione.setIdOffertaPiuAlta(offerta.getId());
				return astaInSessione;
				
				
			} else {
				System.out.println("errore , soldi inseriti sono minori del prezzo partenza");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new Exception();
	}
	

}
