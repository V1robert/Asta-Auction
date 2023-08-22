package banca.models;


import java.util.List;
import java.util.Objects;

public class Conto {

	
	private Integer nConto;
	private Utente utente;
	private Double saldo;
	private List<Transazione>listaTransazioni;
	private StatoConto statoConto; 
	

	public Conto() {
		statoConto = new StatoConto();
		utente = new Utente();
		statoConto.setId(2);
	}
	 
	//GETTERS AND SETTERS
	public void setnConto(Integer nConto) {
		this.nConto = nConto;
	}
	public Integer getnConto() {
		return nConto;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<Transazione> getListaTransazioni() {
		return listaTransazioni;
	}
	public void setListaTransazioni(List<Transazione> listaTransazioni) {
		this.listaTransazioni = listaTransazioni;
	}
	public StatoConto getStatoConto() {
		return statoConto;
	}
	public void setStatoConto(StatoConto statoConto) {
		this.statoConto = statoConto;
	}
	//OVERRIDE
	@Override
	public int hashCode() {
		return Objects.hash(nConto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conto other = (Conto) obj;
		return Objects.equals(nConto, other.nConto);
	}
	@Override
	public String toString() {
		return "Conto [nConto=" + nConto + ", utente=" + utente + ", saldo=" + saldo +  ", statoconto =" + statoConto + "]";
	}






	
	
}
