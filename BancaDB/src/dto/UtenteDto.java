package dto;

import java.util.List;
import java.util.Objects;

public class UtenteDto {


	private String email;
	private String pass;
	private Integer importoDaPagare;
	private String codiceFiscale;
    private List<UtenteDto> listaUtenti;
    
	public UtenteDto() {

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

	public Integer getImportoDaPagare() {
		return importoDaPagare;
	}

	public void setImportoDaPagare(Integer importoDaPagare) {
		this.importoDaPagare = importoDaPagare;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public List<UtenteDto> getListaUtenti() {
		return listaUtenti;
	}

	public void setListaUtenti(List<UtenteDto> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale, email, importoDaPagare, listaUtenti, pass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtenteDto other = (UtenteDto) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(email, other.email)
				&& Objects.equals(importoDaPagare, other.importoDaPagare)
				&& Objects.equals(listaUtenti, other.listaUtenti) && Objects.equals(pass, other.pass);
	}

	@Override
	public String toString() {
		return "UtenteDto [email=" + email + ", pass=" + pass + ", importoDaPagare=" + importoDaPagare
				+ ", codiceFiscale=" + codiceFiscale + ", listaUtenti=" + listaUtenti + "]";
	}
 

}