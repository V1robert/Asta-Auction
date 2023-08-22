package models;

import java.util.Objects;

public class StatoAsta {

	private Integer id;
	private String stato;
	private String desc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(desc, id, stato);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatoAsta other = (StatoAsta) obj;
		return Objects.equals(desc, other.desc) && Objects.equals(id, other.id) && Objects.equals(stato, other.stato);
	}
	@Override
	public String toString() {
		return "StatoAsta [id=" + id + ", stato=" + stato + ", desc=" + desc + "]";
	}
	
	
}
