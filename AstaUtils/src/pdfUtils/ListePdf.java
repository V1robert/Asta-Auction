package pdfUtils;

import java.util.List;

import models.Articolo;
import models.Asta;
import models.Tag;
import models.Utente;

public class ListePdf {

	private List<Tag> listaTag;
	private List<Articolo> listaArticolo;
	private List<Utente> listaUtenti;
	private List<Asta> listaAste;
	
	public List<Tag> getListaTag() {
		return listaTag;
	}
	public void setListaTag(List<Tag> listaTag) {
		this.listaTag = listaTag;
	}
	public List<Articolo> getListaArticolo() {
		return listaArticolo;
	}
	public void setListaArticolo(List<Articolo> listaArticolo) {
		this.listaArticolo = listaArticolo;
	}
	public List<Utente> getListaUtenti() {
		return listaUtenti;
	}
	public void setListaUtenti(List<Utente> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}
	public List<Asta> getListaAste() {
		return listaAste;
	}
	public void setListaAste(List<Asta> listaAste) {
		this.listaAste = listaAste;
	}
	
	
}
