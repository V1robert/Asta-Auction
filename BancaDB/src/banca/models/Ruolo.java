package banca.models;

import java.util.Objects;

public class Ruolo {
	
	private Integer id; //primary key
	private String  descrizione; //utente, admin, staff
	
	//GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	//OVERRIDE
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", descrizione=" + descrizione + "]";
	}

	
}
