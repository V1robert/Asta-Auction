package enums;

public enum StatoResponse {

	KO("operazione fallita"),
	OK("operazione andata a buon fine");
	
	private String descrizione;


	public String getDescrizione() {
		return descrizione;
	}


	private StatoResponse(String descrizione) {
		this.descrizione = descrizione;
	}
}
