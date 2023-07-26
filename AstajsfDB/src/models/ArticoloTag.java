package models;

import java.util.Objects;

public class ArticoloTag {

	private Integer id;
	private Integer idArticolo;
	private Integer idTag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
	}
	public Integer getIdTag() {
		return idTag;
	}
	public void setIdTag(Integer idTag) {
		this.idTag = idTag;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, idArticolo, idTag);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticoloTag other = (ArticoloTag) obj;
		return Objects.equals(id, other.id) && Objects.equals(idArticolo, other.idArticolo)
				&& Objects.equals(idTag, other.idTag);
	}
	@Override
	public String toString() {
		return "ArticoloTag [id=" + id + ", idArticolo=" + idArticolo + ", idTag=" + idTag + "]";
	}
	
	
	
}
