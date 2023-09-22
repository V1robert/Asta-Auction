package model;

import java.util.List;

import enums.StatoResponse;
import lombok.Data;

@Data
public class EsitoOut {

	private StatoResponse stato;
	private Integer codice;
	private String descrizione;
	private List<String> nomiDeiFile;

}
