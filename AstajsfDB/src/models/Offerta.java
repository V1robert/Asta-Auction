package models;

import java.util.Date;
import java.util.Objects;

public class Offerta {

	private Integer id;
	private Integer idStatoOfferta;
	private Integer idUtente;
	private Integer soldiOfferti;
	private Date dataInizio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdStatoOfferta() {
		return idStatoOfferta;
	}
	public void setIdStatoOfferta(Integer idStatoOfferta) {
		this.idStatoOfferta = idStatoOfferta;
	}
	public Integer getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}
	public Integer getSoldiOfferti() {
		return soldiOfferti;
	}
	public void setSoldiOfferti(Integer soldiOfferti) {
		this.soldiOfferti = soldiOfferti;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataInizio, id, idStatoOfferta, idUtente, soldiOfferti);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offerta other = (Offerta) obj;
		return Objects.equals(dataInizio, other.dataInizio) && Objects.equals(id, other.id)
				&& Objects.equals(idStatoOfferta, other.idStatoOfferta) && Objects.equals(idUtente, other.idUtente)
				&& Objects.equals(soldiOfferti, other.soldiOfferti);
	}
	@Override
	public String toString() {
		return "Offerta [id=" + id + ", idStatoOfferta=" + idStatoOfferta + ", idUtente=" + idUtente + ", soldiOfferti="
				+ soldiOfferti + ", dataInizio=" + dataInizio + "]";
	}
	
	
}
