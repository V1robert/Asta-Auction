package com.example.demo;

import java.io.Serializable;
import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UTENTE")
public class Utente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1168506155843956460L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer idUtente;
	
	@Column(name ="NOME")
	private String nome;

	@Column(name ="COGNOME")
	private String cognome;
	
	@Column(name ="EMAIL")
    private String email;
	
	@Column(name ="PASS")
	private String pass;
	
	@Column(name ="SOLDI")
	private Integer soldi;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="ID_RUOLO",referencedColumnName="ID",nullable=false,insertable=false,updatable=false)
	private Ruolo idRuolo;
	
	@Column(name="CF")
	private String cf;
	
	public Utente() {
		super();
	}
	
	public Utente(Integer idUtente,String cf,String nome,String cognome,String email,String pass,Integer soldi,Integer idRuolo) {
		super();
		this.idUtente= idUtente;
		this.cf =cf;
		this.nome =nome;
		this.cognome = cognome;
		this.email = email;
		this.pass = pass;
		this.soldi = soldi;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getSoldi() {
		return soldi;
	}

	public void setSoldi(Integer soldi) {
		this.soldi = soldi;
	}

	public Ruolo getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(Ruolo idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cf, cognome, email, idRuolo, idUtente, nome, pass, soldi);
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
		return Objects.equals(cf, other.cf) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(email, other.email) && Objects.equals(idRuolo, other.idRuolo)
				&& Objects.equals(idUtente, other.idUtente) && Objects.equals(nome, other.nome)
				&& Objects.equals(pass, other.pass) && Objects.equals(soldi, other.soldi);
	}

	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", pass=" + pass + ", soldi=" + soldi + ", idRuolo=" + idRuolo + ", cf=" + cf + "]";
	}

	
}
