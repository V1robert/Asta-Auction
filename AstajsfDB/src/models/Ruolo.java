package models;

import java.util.Objects;

public class Ruolo {

	private Integer idRuolo;
    private String ruolo;
	public Integer getIdRuolo() {
		return idRuolo;
	}
	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idRuolo, ruolo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ruolo other = (Ruolo) obj;
		return Objects.equals(idRuolo, other.idRuolo) && Objects.equals(ruolo, other.ruolo);
	}
	@Override
	public String toString() {
		return "Ruolo [idRuolo=" + idRuolo + ", ruolo=" + ruolo + "]";
	}
    
    
}
