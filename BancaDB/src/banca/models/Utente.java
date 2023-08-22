package banca.models;

import java.util.List;
import java.util.Objects;

public class Utente {

	private String cf; //primary key
	private String nome;
	private String cognome;
	private String mail;
	private String pwd;
	private String cellulare;
	private Integer idRuolo;
	private Ruolo ruolo;
	private List <Conto>listaConti;
	private Integer id;
	


	
	public Utente() {
		idRuolo = 2;
	}
	
	//GETTERS AND SETTERS
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public Integer getIdRuolo() {
		return idRuolo;
	}
	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}
	
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public List <Conto> getListaConti() {
		return listaConti;
	}
	public void setListaConti(List <Conto> listaConti) {
		this.listaConti = listaConti;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	//OVERRIDE
	@Override
	public int hashCode() {
		return Objects.hash(cf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(cf, other.cf);
	}
	@Override
	public String toString() {
		return "Utente [cf=" + cf + ", nome=" + nome + ", cognome=" + cognome + ", cellulare=" + cellulare + ", ruolo="
				+ ruolo + ", listaConti=" + listaConti + "]";
	}



	
}
	

	

	


