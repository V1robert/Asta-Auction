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
import interfaces.TagInterface;
import inviapdf.SendEmailPDF;
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

	public void inviaEmailPdf(Asta asta) {
		SendEmailPDF mail = new SendEmailPDF();
		System.out.println(asta);
		mail.inviaEmailProva(asta);

	}

	public void inviaEmailPdfAdmin() {
		SendEmailPDF mail = new SendEmailPDF();
		listaTuttiArticoli.clear();
		listaDelleAste.clear();
		listaTuttiTag.clear();
		listaTuttiUtenti.clear();
		for (int x = 0; x < listaPDFscelti.size(); x++) {

			if (listaPDFscelti.get(x).equalsIgnoreCase("TuttiArticoli")) {
				listaTuttiArticoli = articoloInterface.findAll();
				System.out.println(listaPDFscelti.get(x));

			} else if (listaPDFscelti.get(x).equalsIgnoreCase("TutteAste")) {
				listaDelleAste = astaInterface.findAllAste();
				System.out.println(listaPDFscelti.get(x));
			} else if (listaPDFscelti.get(x).equalsIgnoreCase("TuttiTag")) {
				listaTuttiTag = tagInterface.findAll();
				System.out.println(listaPDFscelti.get(x));
			} else if (listaPDFscelti.get(x).equalsIgnoreCase("TuttiUtenti")) {
				listaTuttiUtenti = userBean.getUtenteInterface().findAll();
				System.out.println(listaPDFscelti.get(x));

			} else {
				System.out.println("non hai selezionato altro");
			}

		}
		mail.inviaEmailAdmin(listaTuttiArticoli, listaDelleAste, listaTuttiTag, listaTuttiUtenti);
	}

	public void controllaPrezzoInserito() {
		if (asta.getPrezzoPartenza() > 1) {
			buttonDisabled = false;
		}
	}

	public void trovaArticoli() {
		try {

			articolo.setId_utente(userBean.getLoggedUtente().getIdUtente());

			listaArticoli = articoloInterface.findArticoliUtente(articolo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void trovaLeAsteVincenti() {
		Asta astaa = new Asta();
		astaa.setIdUtentePropietario(userBean.getLoggedUtente().getIdUtente());
		listaDelleAsteVinte = astaInterface.vediLeAsteVincentii(astaa);

		for (int x = 0; x < listaDelleAsteVinte.size(); x++) {
			if (null == listaDelleAsteVinte.get(x).getPrezzoVenduto()) {
				listaDelleAsteVinte.remove(x);
			}
		}
	}

	// il compare to confronta 2 date , se sono uguali torna 0 , se la data 1 verra
	// dopo la data 2 torna piu di 0 , se la data 1 e minore della data 2 torna meno
	// di 0

	public void trovaTutteLeAste() {
		try {
			listaDelleAste = astaInterface.findAllAste();

			for (int x = 0; x < listaDelleAste.size(); x++) {

				if (listaDelleAste.get(x).getDataScadenza().compareTo(dataDiOggi) == 0) {
					System.out.println("l'asta scade oggi");
				} else if (listaDelleAste.get(x).getDataScadenza().compareTo(dataDiOggi) > 0) {
					System.out.println("l'asta è ancora valida");

				} else if (listaDelleAste.get(x).getDataScadenza().compareTo(dataDiOggi) < 0) {
					System.out.println("l'asta non è piu valida");

					if (null == listaDelleAste.get(x).getPrezzoVenduto()
							&& null != listaDelleAste.get(x).getIdOffertaPiuAlta()) {
						listaDelleAste.get(x).setPrezzoVenduto(listaDelleAste.get(x).getOfferta().getSoldiOfferti());
						Asta astaDaTerminare = listaDelleAste.get(x);

						System.out.println(astaDaTerminare);
						astaInterface.astaFinita(astaDaTerminare);

					} else if (null == listaDelleAste.get(x).getIdOffertaPiuAlta()) {
						System.out.println("nessuno ha mai fatto un offerta che superi il prezzo minimo di" + " "
								+ listaDelleAste.get(x).getPrezzoPartenza());

					} else {
						System.out.println(
								"l'offerta vincente è " + " " + listaDelleAste.get(x).getOfferta().getSoldiOfferti());
					}

					listaDelleAste.remove(x);
				}

			}
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

}
