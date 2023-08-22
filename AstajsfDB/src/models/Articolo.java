package models;

import java.util.Arrays;
import java.util.Objects;

public class Articolo {

	private String nomeArticolo;
	private Integer idUtente;
	private String desc;
	private Integer id;
	private byte[] img;
	
	public String getNomeArticolo() {
		return nomeArticolo;
	}
	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}
	public Integer getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Integer idUtente) {
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
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(img);
		result = prime * result + Objects.hash(desc, id, idUtente, nomeArticolo);
		return result;
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
				&& Objects.equals(idUtente, other.idUtente) && Arrays.equals(img, other.img)
				&& Objects.equals(nomeArticolo, other.nomeArticolo);
	}
	@Override
	public String toString() {
		return "Articolo [nomeArticolo=" + nomeArticolo + ", idUtente=" + idUtente + ", desc=" + desc + ", id=" + id
				+ ", img=" + Arrays.toString(img) + "]";
	}
	
	
	
	
	
	
	
	
}
