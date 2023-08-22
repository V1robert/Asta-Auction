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
		try {
			astaInterface.update(offertaInterface.inserimentoOfferta(offerta,astaInSessione,userBean.getLoggedUtente()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userBean.vediTutteAste();
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
