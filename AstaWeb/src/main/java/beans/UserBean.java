package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import interfaces.ArticoloInterface;
import interfaces.AstaInterface;
import interfaces.UtenteInterface;
import models.Articolo;
import models.Asta;
import models.Utente;
import regex.RegEx;

@Named
@SessionScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5022669426687458041L;

	@Inject
	private ArticoloBean articoloBean;
	@Inject
	private AstaBean astaBean;
	@Inject
	private OffertaBean offertaBean;

	@EJB
	private UtenteInterface utenteInterface;

	@EJB
	private ArticoloInterface articoloInterface;
	
	@EJB
	private AstaInterface astaInterface;
	
	
	
	private RegEx regEx;
	private Utente utente;
	private Utente nuovoUser;
	private Utente loggedUtente;
	private Articolo articolo;
	private Boolean loggato = false;
	private Boolean showLogin = false;
	private Boolean showRegister = false;
	private Boolean showArticolo = false;
	private Boolean showAsta = false;
	private Boolean vediTutteLeAste = false;
	private Boolean vediHome = true;
	private Boolean showOfferta=false;
	private Boolean vediLeTueAsteVinte=false;
	private Boolean admin=false;
	private Boolean vediPaginaAdmin=false;
	private Boolean provabutton=false;
	
	private List<Articolo> listaDegliArticoli;
	
	private List<Asta> listaAste;
	
	private String codiceFiscale;

	public void cambiabutton() {
		provabutton=true;
	}
	@PostConstruct
	public void init() {
		utente = new Utente();
		codiceFiscale = new String();
		trovaTutteLeAste();
	}
	
	
	public void pagaAsta(Asta astaDaPagare) {
		utenteInterface.pagaAsta(loggedUtente,astaDaPagare.getPrezzoVenduto(),codiceFiscale);
		System.out.println(codiceFiscale);
	}
	
	
	public StreamedContent scaricaPdf() {
		//corrotto
		StreamedContent a = null;
		return a;
	}
	
	public void trovaITuoiArticoli() {
		try {
			articolo=new Articolo();
			articolo.setIdUtente(loggedUtente.getIdUtente());
			
			listaDegliArticoli=articoloInterface.findArticoliUtente(articolo);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void trovaTutteLeAste() {
		listaAste= new ArrayList<Asta>();
		listaAste=astaInterface.findAllAste();
	}
	
	public void loginASpring() {
		try {
			loggedUtente=utenteInterface.loginSpring(loggedUtente, utente);
			loggato = true;
			showLogin = false;
			showArticolo = false;
			showRegister = false;
			vediHome = true;
			vediTutteLeAste = false;
			showAsta = false;
			showOfferta=false;
		    vediPaginaAdmin=false;
			vediLeTueAsteVinte=false;
			

			trovaITuoiArticoli();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void login() {
		try {
			loggedUtente=utenteInterface.loginSpring(loggedUtente, utente);
			loggato = true;
			showLogin = false;
			showArticolo = false;
			showRegister = false;
			vediHome = true;
			vediTutteLeAste = false;
			showAsta = false;
			showOfferta=false;
		    vediPaginaAdmin=false;
			vediLeTueAsteVinte=false;
			if(utenteInterface.controllaRuolo(loggedUtente)) {
				admin=true;
			}

			trovaITuoiArticoli();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	

	public void register() {

		System.out.println(utente.toString());
		utenteInterface.insert(utente);
		System.out.println("sto nel metodo register");
		utente = new Utente();
		loggato = true;
		showLogin = false;
		showArticolo = false;
		showRegister = false;
		vediHome = true;
		vediTutteLeAste = false;
		showAsta = false;
		showOfferta=false;
	    vediPaginaAdmin=false;
	    vediLeTueAsteVinte=false;
		System.out.println(showRegister + "register boolean");
		System.out.println(showLogin + "login boolean");

	}
	
	public void vaiAllaPaginaAdmin() {
		showLogin = false;
		showRegister = false;
		showArticolo = false;
		showAsta = false;
		vediTutteLeAste = false;
		vediHome = false;
		showOfferta=false;
		vediLeTueAsteVinte=false;
		vediPaginaAdmin=true;
	}
	

	public void vaiAlLogin() {
		loggato = false;
		admin=false;
		showLogin = true;
		showArticolo = false;
		showRegister = false;
		vediHome = false;
		vediTutteLeAste = false;
		showAsta = false;
		showOfferta=false;
	    vediPaginaAdmin=false;
	    vediLeTueAsteVinte=false;
	}
	
	public void vediTutteLeTueAsteVinte() {
		showLogin = false;
		showArticolo = false;
		showRegister = false;
		vediHome = false;
		vediTutteLeAste = false;
		showAsta = false;
		showOfferta=false;
	    vediPaginaAdmin=false;
	    vediLeTueAsteVinte=true;
	}
	
	public void logOut() {
		loggedUtente=null;
		loggato = false;
		showLogin = false;
		showRegister = false;
		showArticolo = false;
		showAsta = false;
		vediTutteLeAste = false;
		vediHome = true;
		showOfferta=false;
		vediLeTueAsteVinte=false;
		admin=false;
	}

	public void vaiAlRegister() {
		admin=false;
		loggato=false;
		showLogin = false;
		showArticolo = false;
		showRegister = true;
		vediHome = false;
		vediTutteLeAste = false;
		showAsta = false;
		showOfferta=false;
	    vediPaginaAdmin=false;
	    vediLeTueAsteVinte=false;
	}

	public void vaiAlCreaArticolo() {
		loggato = true;
		showLogin = false;
		showArticolo = true;
		showRegister = false;
		vediHome = false;
		vediTutteLeAste = false;
		showAsta = false;
		showOfferta=false;
	    vediPaginaAdmin=false;
	    vediLeTueAsteVinte=false;
	}

	public void vediAsta() {
		loggato = true;
		showLogin = false;
		showArticolo = false;
		showRegister = false;
		vediHome = false;
		vediTutteLeAste = false;
		showAsta = true;
		showOfferta=false;
	    vediPaginaAdmin=false;
	    vediLeTueAsteVinte=false;
	}
	public void vediOfferta() {
		showLogin = false;
		showArticolo = false;
		showRegister = false;
		vediHome = false;
		vediTutteLeAste = false;
		showAsta = false;
		showOfferta=true;
	    vediPaginaAdmin=false;
	    vediLeTueAsteVinte=false;
	}

	public void creaArticoloo() {
		articoloBean.creaArticolo(loggedUtente.getIdUtente());
	}

	public void vediTutteAste() {
		showLogin = false;
		showRegister = false;
		showArticolo = false;
		showAsta = false;
		vediTutteLeAste = true;
		vediHome = false;
		showOfferta=false;
		astaBean.trovaTutteLeAste();
	}

	public void vaiAllaHome() {
		showLogin = false;
		showArticolo = false;
		showRegister = false;
		vediHome = true;
		vediTutteLeAste = false;
		showAsta = false;
		showOfferta=false;
	    vediPaginaAdmin=false;
	    vediLeTueAsteVinte=false;
	}

	public UtenteInterface getUtenteInterface() {
		return utenteInterface;
	}

	public void setUtenteInterface(UtenteInterface utenteInterface) {
		this.utenteInterface = utenteInterface;
	}

	public Utente getUtente() {
		return utente;
	}

	public Boolean getVediHome() {
		return vediHome;
	}

	public void setVediHome(Boolean vediHome) {
		this.vediHome = vediHome;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Utente getLoggedUtente() {
		return loggedUtente;
	}

	public void setLoggedUtente(Utente loggedUtente) {
		this.loggedUtente = loggedUtente;
	}

	public Utente getNuovoUser() {
		return nuovoUser;
	}

	public void setNuovoUser(Utente nuovoUser) {
		this.nuovoUser = nuovoUser;
	}

	public Boolean getShowLogin() {
		return showLogin;
	}

	public void setShowLogin(Boolean showLogin) {
		this.showLogin = showLogin;
	}

	public Boolean getShowRegister() {
		return showRegister;
	}

	public void setShowRegister(Boolean showRegister) {
		this.showRegister = showRegister;
	}

	
	public ArticoloInterface getArticoloInterface() {
		return articoloInterface;
	}

	public void setArticoloInterface(ArticoloInterface articoloInterface) {
		this.articoloInterface = articoloInterface;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public List<Articolo> getListaDegliArticoli() {
		return listaDegliArticoli;
	}

	public void setListaDegliArticoli(List<Articolo> listaDegliArticoli) {
		this.listaDegliArticoli = listaDegliArticoli;
	}

	public ArticoloBean getArticoloBean() {
		return articoloBean;
	}

	public void setArticoloBean(ArticoloBean articoloBean) {
		this.articoloBean = articoloBean;
	}

	public Boolean getShowArticolo() {
		return showArticolo;
	}

	public void setShowArticolo(Boolean showArticolo) {
		this.showArticolo = showArticolo;
	}

	public Boolean getLoggato() {
		return loggato;
	}

	public void setLoggato(Boolean loggato) {
		this.loggato = loggato;
	}

	public AstaBean getAstaBean() {
		return astaBean;
	}

	public OffertaBean getOffertaBean() {
		return offertaBean;
	}

	public void setOffertaBean(OffertaBean offertaBean) {
		this.offertaBean = offertaBean;
	}

	public void setAstaBean(AstaBean astaBean) {
		this.astaBean = astaBean;
	}

	public Boolean getShowAsta() {
		return showAsta;
	}

	public Boolean getVediTutteLeAste() {
		return vediTutteLeAste;
	}

	public void setVediTutteLeAste(Boolean vediTutteLeAste) {
		this.vediTutteLeAste = vediTutteLeAste;
	}

	public void setShowAsta(Boolean showAsta) {
		this.showAsta = showAsta;
	}

	public Boolean getShowOfferta() {
		return showOfferta;
	}

	public RegEx getRegEx() {
		return regEx;
	}

	public void setRegEx(RegEx regEx) {
		this.regEx = regEx;
	}

	public Boolean getVediLeTueAsteVinte() {
		return vediLeTueAsteVinte;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void setVediLeTueAsteVinte(Boolean vediLeTueAsteVinte) {
		this.vediLeTueAsteVinte = vediLeTueAsteVinte;
	}

	public Boolean getVediPaginaAdmin() {
		return vediPaginaAdmin;
	}

	public void setVediPaginaAdmin(Boolean vediPaginaAdmin) {
		this.vediPaginaAdmin = vediPaginaAdmin;
	}

	public void setShowOfferta(Boolean showOfferta) {
		this.showOfferta = showOfferta;
	}
	
	public AstaInterface getAstaInterface() {
		return astaInterface;
	}
	public void setAstaInterface(AstaInterface astaInterface) {
		this.astaInterface = astaInterface;
	}
	public List<Asta> getListaAste() {
		return listaAste;
	}
	public void setListaAste(List<Asta> listaAste) {
		this.listaAste = listaAste;
	}
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public Boolean getProvabutton() {
		return provabutton;
	}
	public void setProvabutton(Boolean provabutton) {
		this.provabutton = provabutton;
	}
	
  
	
}
