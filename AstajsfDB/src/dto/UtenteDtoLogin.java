package dto;

import java.util.Objects;

public class UtenteDtoLogin {


	private String email;
	private String pass;
    
	public UtenteDtoLogin() {

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

	

	@Override
	public int hashCode() {
		return Objects.hash(email, pass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtenteDtoLogin other = (UtenteDtoLogin) obj;
		return Objects.equals(email, other.email) && Objects.equals(pass, other.pass);
	}

	@Override
	public String toString() {
		return "UtenteDto [email=" + email + ", pass=" + pass + "]";
	}

	

	
 

}
