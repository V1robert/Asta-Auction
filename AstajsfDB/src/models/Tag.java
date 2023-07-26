package models;

import java.util.List;
import java.util.Objects;

public class Tag {

	private Integer id;
	private String descrizioneTag;
	private List<Tag> listaTag;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescrizioneTag() {
		return descrizioneTag;
	}
	public void setDescrizioneTag(String descrizioneTag) {
		this.descrizioneTag = descrizioneTag;
	}
	public List<Tag> getListaTag() {
		return listaTag;
	}
	public void setListaTag(List<Tag> listaTag) {
		this.listaTag = listaTag;
	}
	@Override
	public int hashCode() {
		return Objects.hash(descrizioneTag, id, listaTag);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		return Objects.equals(descrizioneTag, other.descrizioneTag) && Objects.equals(id, other.id)
				&& Objects.equals(listaTag, other.listaTag);
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", descrizioneTag=" + descrizioneTag + ", listaTag=" + listaTag + "]";
	}
	
	
}
