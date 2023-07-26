package models;

import java.util.Objects;

public class Articolo {

	private String nomeArticolo;
	private Integer idUtente;
	private String desc;
	private Integer id;
	public String getNome_articolo() {
		return nomeArticolo;
	}
	public void setNome_articolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}
	public Integer getId_utente() {
		return idUtente;
	}
	public void setId_utente(Integer idUtente) {
		this.idUtente = idUtente;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(desc, id, idUtente, nomeArticolo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		return Objects.equals(desc, other.desc) && Objects.equals(id, other.id)
				&& Objects.equals(idUtente, other.idUtente) && Objects.equals(nomeArticolo, other.nomeArticolo);
	}
	@Override
	public String toString() {
		return "Articolo [nomeArticolo=" + nomeArticolo + ", idUtente=" + idUtente + ", desc=" + desc + ", id=" + id
				+ "]";
	}
	
	
	
	
}
