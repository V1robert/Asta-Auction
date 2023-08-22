package com.example.demo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RUOLO")
public class Ruolo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 252543373231067456L;

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "RUOLO")
	private String ruolo;
	
	public Ruolo() {
		super();
	}
	
	public Ruolo(Integer id,String ruolo) {
		super();
		this.id=id;
		this.ruolo=ruolo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

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
		return "Ruolo [id=" + id + ", ruolo=" + ruolo + "]";
	}
	
}
