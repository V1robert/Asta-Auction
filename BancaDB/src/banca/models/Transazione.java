package banca.models;

import java.util.Date;
import java.util.Objects;

public class Transazione {

	private Integer id;
	private Double importo;
	private Date data;
	private TipoTransazione tipologia;
	private StatoTransazione statoTransazione;
	private Conto conto;
	
	
	
	public Transazione() {
		this.data= new Date();
		statoTransazione = new StatoTransazione();
		this.statoTransazione.setId(1);
		conto = new Conto();
		tipologia = new TipoTransazione();
	}
	
	//GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Double getImporto() {
		return importo;
	}
	public void setImporto(Double importo) {
		this.importo = importo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public TipoTransazione getTipoTransazione() {
		return tipologia;
	}
	public void setTipoTransazione(TipoTransazione tipoTransazione) {
		this.tipologia = tipoTransazione;
	}
	public StatoTransazione getStatoTransazione() {
		return statoTransazione;
	}
	public void setStatoTransazione(StatoTransazione statoTransazione) {
		this.statoTransazione = statoTransazione;
	}
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		this.conto = conto;
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
		Transazione other = (Transazione) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Transazione [id=" + id +  ", importo=" + importo + ", data=" + data + ", tipoTransazione=" + tipologia
				+ ", statoTransazione=" + statoTransazione + ", conto=" + conto + "]";
	}
	
	
	
	
	
	
	
}
