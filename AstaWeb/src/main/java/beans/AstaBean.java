package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


import interfaces.ArticoloInterface;
import interfaces.AstaInterface;
import interfaces.InviaEmailInterface;
import interfaces.TagInterface;
import models.Articolo;
import models.Asta;
import models.Tag;
import models.Utente;

@Named
@SessionScoped
public class AstaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6637672046347864351L;

	@EJB
	private AstaInterface astaInterface;

	@EJB
	private InviaEmailInterface inviaEmailInterface;

	@EJB
	private ArticoloInterface articoloInterface;

	@EJB
	private TagInterface tagInterface;

	@Inject
	private UserBean userBean;

	@Inject
	private ArticoloBean articoloBean;

	private Articolo articolo;

	private Asta asta;

	private Tag tag;

	private List<Utente> listaTuttiUtenti;

	private List<Tag> listaTuttiTag;

	private List<Articolo> listaTuttiArticoli;

	private List<Articolo> listaArticoli;

	private List<Asta> listaDelleAste;

	private List<Asta> listaDelleAsteVinte;

	private List<String> listaPDFscelti;

	private Boolean articoliPDF = true;

	private Boolean tagPDF = true;

	private Boolean astePDF = true;

	private Boolean utentiPDF = true;

	private Date dataDiOggi;

	private Boolean buttonDisabled = true;

	private Boolean salvaPdf = false;
	
	

	@PostConstruct
	public void init() {

		tag = new Tag();

		listaTuttiArticoli = new ArrayList<Articolo>();
		listaDelleAste = new ArrayList<Asta>();
		listaArticoli = new ArrayList<Articolo>();
		listaPDFscelti = new ArrayList<String>();
		listaTuttiTag = new ArrayList<Tag>();
		listaTuttiUtenti = new ArrayList<Utente>();
		dataDiOggi = new Date();
		articolo = new Articolo();
		asta = new Asta();
		trovaArticoli();
		trovaTutteLeAste();
		trovaLeAsteVincenti();
	}

	public void inviaEmailPdf(Boolean sceltaPdf) {

		inviaEmailInterface.inviaListePdf(listaPDFscelti, userBean.getLoggedUtente(), sceltaPdf);
	}
	
	public void inviaEmailPdf(Asta asta,Boolean sceltaPdf) {

		inviaEmailInterface.inviaListePdf(listaPDFscelti, userBean.getLoggedUtente(), sceltaPdf);
	}

	public void salvaPdfLocale(Boolean sceltaPdf) {

		try {
			inviaEmailInterface.salvaInLocal(listaPDFscelti, userBean.getLoggedUtente(), sceltaPdf);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void controllaPrezzoInserito() {
		if (asta.getPrezzoPartenza() > 1) {
			buttonDisabled = false;
		}
	}
	

	public void trovaArticoli() {
		try {
			articolo.setIdUtente(userBean.getLoggedUtente().getIdUtente());

			listaArticoli = articoloInterface.findArticoliUtente(articolo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void scaricaAstaPdfLib() {
		inviaEmailInterface.creaPdfAstaConPdfLib();
	}

	/*
	 * public DefaultStreamedContent creaFoto(byte[] foto) {
	 * 
	 * for(int x=0;x<listaArticoli.size();x++) { if(null != listaArticoli.get(x)) {
	 * DefaultStreamedContent streamedContent = DefaultStreamedContent.builder()
	 * .name("panda") .contentType("application/octet-stream") .stream(() -> new
	 * ByteArrayInputStream(listaArticoli.get(x).getImg())).build();
	 * 
	 * return streamedContent; } } return null;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	public void trovaLeAsteVincenti() {
		Asta astaa = new Asta();
		listaDelleAsteVinte = astaInterface.vediLeAsteVincentii(astaa,userBean.getLoggedUtente());
	}

	public void trovaTutteLeAste() {
		try {
			listaDelleAste = astaInterface.findAllAste();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void entraInQuestaAsta(Asta asta) {
		this.asta = asta;
	}

	public void inserisciAsta(Integer idDelArticolo, Integer idDelUtentePropietario) {
		tagInterface.insert(tag);
		asta.setIdTag(tag.getId());
		asta.setIdArticolo(idDelArticolo);
		asta.setIdUtentePropietario(idDelUtentePropietario);
		astaInterface.insert(asta);

	}

	public ArticoloInterface getArticoloInterface() {
		return articoloInterface;
	}

	public void setArticoloInterface(ArticoloInterface articoloInterface) {
		this.articoloInterface = articoloInterface;
	}

	public ArticoloBean getArticoloBean() {
		return articoloBean;
	}

	public void setArticoloBean(ArticoloBean articoloBean) {
		this.articoloBean = articoloBean;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Boolean getSalvaPdf() {
		return salvaPdf;
	}

	public void setSalvaPdf(Boolean salvaPdf) {
		this.salvaPdf = salvaPdf;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public Asta getAsta() {
		return asta;
	}

	public void setAsta(Asta asta) {
		this.asta = asta;
	}

	public List<Articolo> getListaArticoli() {
		return listaArticoli;
	}

	public void setListaArticoli(List<Articolo> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}

	public AstaInterface getAstaInterface() {
		return astaInterface;
	}

	public void setAstaInterface(AstaInterface astaInterface) {
		this.astaInterface = astaInterface;
	}

	public List<Asta> getListaDelleAste() {
		return listaDelleAste;
	}

	public void setListaDelleAste(List<Asta> listaDelleAste) {
		this.listaDelleAste = listaDelleAste;
	}

	public Date getDataDiOggi() {
		return dataDiOggi;
	}

	public void setDataDiOggi(Date dataDiOggi) {
		this.dataDiOggi = dataDiOggi;
	}

	public Boolean getButtonDisabled() {
		return buttonDisabled;
	}

	public TagInterface getTagInterface() {
		return tagInterface;
	}

	public void setTagInterface(TagInterface tagInterface) {
		this.tagInterface = tagInterface;
	}

	public Tag getTag() {
		return tag;
	}

	public List<Asta> getListaDelleAsteVinte() {
		return listaDelleAsteVinte;
	}

	public void setListaDelleAsteVinte(List<Asta> listaDelleAsteVinte) {
		this.listaDelleAsteVinte = listaDelleAsteVinte;
	}

	public Boolean getArticoliPDF() {
		return articoliPDF;
	}

	public void setArticoliPDF(Boolean articoliPDF) {
		this.articoliPDF = articoliPDF;
	}

	public Boolean getTagPDF() {
		return tagPDF;
	}

	public void setTagPDF(Boolean tagPDF) {
		this.tagPDF = tagPDF;
	}

	public Boolean getAstePDF() {
		return astePDF;
	}

	public void setAstePDF(Boolean astePDF) {
		this.astePDF = astePDF;
	}

	public Boolean getUtentiPDF() {
		return utentiPDF;
	}

	public void setUtentiPDF(Boolean utentiPDF) {
		this.utentiPDF = utentiPDF;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<String> getListaPDFscelti() {
		return listaPDFscelti;
	}

	public List<Utente> getListaTuttiUtenti() {
		return listaTuttiUtenti;
	}

	public void setListaTuttiUtenti(List<Utente> listaTuttiUtenti) {
		this.listaTuttiUtenti = listaTuttiUtenti;
	}

	public List<Tag> getListaTuttiTag() {
		return listaTuttiTag;
	}

	public void setListaTuttiTag(List<Tag> listaTuttiTag) {
		this.listaTuttiTag = listaTuttiTag;
	}

	public List<Articolo> getListaTuttiArticoli() {
		return listaTuttiArticoli;
	}

	public void setListaTuttiArticoli(List<Articolo> listaTuttiArticoli) {
		this.listaTuttiArticoli = listaTuttiArticoli;
	}

	public void setListaPDFscelti(List<String> listaPDFscelti) {
		this.listaPDFscelti = listaPDFscelti;
	}

	public void setButtonDisabled(Boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	public InviaEmailInterface getInviaEmailInterface() {
		return inviaEmailInterface;
	}

	public void setInviaEmailInterface(InviaEmailInterface inviaEmailInterface) {
		this.inviaEmailInterface = inviaEmailInterface;
	}

	

	
}
