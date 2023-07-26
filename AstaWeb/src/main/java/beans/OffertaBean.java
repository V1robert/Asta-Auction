package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import interfaces.AstaInterface;
import interfaces.OffertaInterface;
import models.Asta;
import models.Offerta;

@Named
@SessionScoped
public class OffertaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4950437131797469900L;

	@EJB
	private AstaInterface astaInterface;
	@EJB
	private OffertaInterface offertaInterface;

	@Inject
	private UserBean userBean;

	private Offerta offerta;

	private Asta astaInSessione;

	@PostConstruct
	public void init() {
		offerta = new Offerta();
		astaInSessione = new Asta();
	}

	public void vediAsta(Asta asta) {
		userBean.vediOfferta();
		setAstaInSessione(asta);
		System.out.println(asta);
		System.out.println(astaInSessione);

	}

	public void inserisciOfferta() {
		offerta.setIdUtente(userBean.getLoggedUtente().getIdUtente());

		

		try {
			if (null == astaInSessione.getOfferta() 
					|| astaInSessione.getOfferta().getSoldiOfferti() < offerta.getSoldiOfferti()
							&&astaInSessione.getPrezzoPartenza()<=offerta.getSoldiOfferti()) {
				
				offertaInterface.insert(offerta);
				
				System.out.println(astaInSessione.getPrezzoPartenza()+"soldi in partenza");
				
				offerta = offertaInterface.trovaOffertaDelUtente(offerta);
				astaInSessione.setIdOffertaPiuAlta(offerta.getId());
				astaInterface.update(astaInSessione);
				
				userBean.vediTutteAste();
			} else {
				System.out.println("errore , soldi inseriti sono minori del prezzo partenza");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OffertaInterface getOffertaInterface() {
		return offertaInterface;
	}

	public void setOffertaInterface(OffertaInterface offertaInterface) {
		this.offertaInterface = offertaInterface;
	}

	public Offerta getOfferta() {
		return offerta;
	}

	public void setOfferta(Offerta offerta) {
		this.offerta = offerta;
	}

	public Asta getAstaInSessione() {
		return astaInSessione;
	}

	public void setAstaInSessione(Asta astaInSessione) {
		this.astaInSessione = astaInSessione;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
