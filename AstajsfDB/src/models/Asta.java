package models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Asta {

	
	private Integer id;
	private Date dataScadenza;
	private Integer prezzoPartenza;
	private Integer prezzoVenduto;
	private Integer idArticolo;
	private Integer idUtentePropietario;
	private Integer idOffertaPiuAlta;
	private Integer idTag;
	private List<Asta> listaAste;
	private Utente utente;
	private Tag tag;
	private Offerta offerta;
	private Articolo articolo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public Integer getPrezzoPartenza() {
		return prezzoPartenza;
	}
	public void setPrezzoPartenza(Integer prezzoPartenza) {
		this.prezzoPartenza = prezzoPartenza;
	}
	public Integer getPrezzoVenduto() {
		return prezzoVenduto;
	}
	public void setPrezzoVenduto(Integer prezzoVenduto) {
		this.prezzoVenduto = prezzoVenduto;
	}
	public Integer getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
	}
	public Integer getIdUtentePropietario() {
		return idUtentePropietario;
	}
	public void setIdUtentePropietario(Integer idUtentePropietario) {
		this.idUtentePropietario = idUtentePropietario;
	}
	public Integer getIdOffertaPiuAlta() {
		return idOffertaPiuAlta;
	}
	public void setIdOffertaPiuAlta(Integer idOffertaPiuAlta) {
		this.idOffertaPiuAlta = idOffertaPiuAlta;
	}
	public Integer getIdTag() {
		return idTag;
	}
	public void setIdTag(Integer idTag) {
		this.idTag = idTag;
	}
	public List<Asta> getListaAste() {
		return listaAste;
	}
	public void setListaAste(List<Asta> listaAste) {
		this.listaAste = listaAste;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public Offerta getOfferta() {
		return offerta;
	}
	public void setOfferta(Offerta offerta) {
		this.offerta = offerta;
	}
	public Articolo getArticolo() {
		return articolo;
	}
	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(articolo, dataScadenza, id, idArticolo, idOffertaPiuAlta, idTag, idUtentePropietario,
				listaAste, offerta, prezzoPartenza, prezzoVenduto, tag, utente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asta other = (Asta) obj;
		return Objects.equals(articolo, other.articolo) && Objects.equals(dataScadenza, other.dataScadenza)
				&& Objects.equals(id, other.id) && Objects.equals(idArticolo, other.idArticolo)
				&& Objects.equals(idOffertaPiuAlta, other.idOffertaPiuAlta) && Objects.equals(idTag, other.idTag)
				&& Objects.equals(idUtentePropietario, other.idUtentePropietario)
				&& Objects.equals(listaAste, other.listaAste) && Objects.equals(offerta, other.offerta)
				&& Objects.equals(prezzoPartenza, other.prezzoPartenza)
				&& Objects.equals(prezzoVenduto, other.prezzoVenduto) && Objects.equals(tag, other.tag)
				&& Objects.equals(utente, other.utente);
	}
	@Override
	public String toString() {
		return "Asta [id=" + id + ", dataScadenza=" + dataScadenza + ", prezzoPartenza=" + prezzoPartenza
				+ ", prezzoVenduto=" + prezzoVenduto + ", idArticolo=" + idArticolo + ", idUtentePropietario="
				+ idUtentePropietario + ", idOffertaPiuAlta=" + idOffertaPiuAlta + ", idTag=" + idTag + ", listaAste="
				+ listaAste + ", utente=" + utente + ", tag=" + tag + ", offerta=" + offerta + ", articolo=" + articolo
				+ "]";
	}
	
	
}

	
	
	