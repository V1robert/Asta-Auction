package beans;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;

import interfaces.ArticoloInterface;
import interfaces.ArticoloTagInterface;
import interfaces.TagInterface;
import models.Articolo;
import models.ArticoloTag;
import models.Tag;

@Named
@SessionScoped
public class ArticoloBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3784754702449599601L;

	@EJB
	private ArticoloInterface articoloInterface;

	@EJB
	private TagInterface tagInterface;

	@EJB
	private ArticoloTagInterface articoloTagInterface;

	private Articolo articolo;

	private ArticoloTag articoloTag;

	private Tag tag;

	private ArrayList<Tag> listaDeiTagArticolo;

	private Articolo articoloTrovato;

	private List<Tag> listaDiTuttiITag;

	private UploadedFile file;

	@PostConstruct
	public void init() {
		articolo = new Articolo();
		tag = new Tag();
		articoloTag = new ArticoloTag();
		articoloTrovato = new Articolo();
		listaDeiTagArticolo = new ArrayList<Tag>();
		trovaTag();
	}

	public void creaUnTag() {
		tagInterface.insert(tag);
		listaDeiTagArticolo.add(tag);
	}

	public void trovaTag() {
		listaDiTuttiITag = tagInterface.findAll();
	}

	public void creaArticolo(Integer idUtente) {
		try {
		Integer idArticoloTrovato ;
		InputStream inputStream=file.getInputStream();
		System.out.println(inputStream);
		byte[] foto=IOUtils.toByteArray(inputStream);
		System.out.println(foto);
		articolo.setImg(foto);
			idArticoloTrovato = articoloInterface.inserisciArticolo(articolo, idUtente);
			
			articoloInterface.creaBridgeArticoloTag(articoloTag, idArticoloTrovato, listaDeiTagArticolo,
					articoloTagInterface, tagInterface);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public ArticoloInterface getArticoloInterface() {
		return articoloInterface;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public void setArticoloInterface(ArticoloInterface articoloInterface) {
		this.articoloInterface = articoloInterface;
	}

	public Tag getTag() {
		return tag;
	}

	public TagInterface getTagInterface() {
		return tagInterface;
	}

	public void setTagInterface(TagInterface tagInterface) {
		this.tagInterface = tagInterface;
	}

	public ArrayList<Tag> getListaDeiTagArticolo() {
		return listaDeiTagArticolo;
	}

	public void setListaDeiTagArticolo(ArrayList<Tag> listaDeiTagArticolo) {
		this.listaDeiTagArticolo = listaDeiTagArticolo;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public ArticoloTagInterface getArticoloTagInterface() {
		return articoloTagInterface;
	}

	public ArticoloTag getArticoloTag() {
		return articoloTag;
	}

	public Articolo getArticoloTrovato() {
		return articoloTrovato;
	}

	public void setArticoloTrovato(Articolo articoloTrovato) {
		this.articoloTrovato = articoloTrovato;
	}

	public void setArticoloTag(ArticoloTag articoloTag) {
		this.articoloTag = articoloTag;
	}

	public void setArticoloTagInterface(ArticoloTagInterface articoloTagInterface) {
		this.articoloTagInterface = articoloTagInterface;
	}

	public List<Tag> getListaDiTuttiITag() {
		return listaDiTuttiITag;
	}

	public void setListaDiTuttiITag(List<Tag> listaDiTuttiITag) {
		this.listaDiTuttiITag = listaDiTuttiITag;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
