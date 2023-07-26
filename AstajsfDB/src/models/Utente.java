package models;
import java.util.Objects;


public class Utente {
	
		private Integer idUtente;
		private String nome;
		private String cognome;
		private String email;
		private String pass;
		private Double soldi;
		private Integer idRuolo;
		private Ruolo ruolo;
		private String cf;
		
		

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

		public Double getSoldi() {
			return soldi;
		}

		public void setSoldi(Double soldi) {
			this.soldi = soldi;
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

		public String getCf() {
			return cf;
		}

		public void setCf(String cf) {
			this.cf = cf;
		}

		@Override
		public int hashCode() {
			return Objects.hash(cf, cognome, email, idRuolo, idUtente, nome, pass, ruolo, soldi);
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
					&& Objects.equals(pass, other.pass) && Objects.equals(ruolo, other.ruolo)
					&& Objects.equals(soldi, other.soldi);
		}

		@Override
		public String toString() {
			return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
					+ ", pass=" + pass + ", soldi=" + soldi + ", idRuolo=" + idRuolo + ", ruolo=" + ruolo + ", cf=" + cf
					+ "]";
		}
		
		
		
	}


