package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import costanti.CostantiClient;
import dto.UtenteDto;
import dto.UtenteDtoLogin;
import models.Ruolo;
import models.Utente;

public class ServiceDelClient {

	public Utente loginSpring(UtenteDtoLogin utente) {
		try {
			String apiUrl = CostantiClient.URL_DEL_LOGIN_SPRING;
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(CostantiClient.HTTP_POST);
			conn.setRequestProperty(CostantiClient.CONTENT_TYPE, CostantiClient.APPLICATION_JSON);
			String jsonPayload = convertiCredenzialiAdUnJson(utente);
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(jsonPayload);
			wr.flush();
			wr.close();

			int responseCode = conn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {

				Utente utenteTornato = convertiJsonUtenteAdObject(convertiRisposta(responseCode, conn));
				return utenteTornato;
			} else {
				System.out.println(CostantiClient.RISPOSTA_ERRORE + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void pagaAsta(UtenteDto utente) {
		try {
			String apiUrl = CostantiClient.URL_DELLA_BANCA;
			URL url = new URL(apiUrl);

			// per stabilire la connessione
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// metodo della richiesta
			conn.setRequestMethod(CostantiClient.HTTP_POST);
			// Setto il tipo di contenuto a json
			conn.setRequestProperty(CostantiClient.CONTENT_TYPE, CostantiClient.APPLICATION_JSON);

			// json payload che dovro mandare al corpo della richiesta
			String jsonPayload = convertiObjectAdUnJson(utente);

			// attivo l'output perche dobbiamo mandare utente all request body
			conn.setDoOutput(true);
			// scriviamo il json payload all output stream
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(jsonPayload);
			wr.flush();
			wr.close();
			// inviamo ora la richiesta post ed aspettiamo una risposta
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {

				UtenteDto utenteTornato = convertiJsonAdObject(convertiRisposta(responseCode, conn));
				System.out.println(utenteTornato);
			} else {
				// in caso contrario
				System.out.println(CostantiClient.RISPOSTA_ERRORE + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// converto il json che è tornato ad un Utente
	public UtenteDto convertiJsonAdObject(String json) {
		JSONObject utenteTornato = new JSONObject(json);

		// trasformo la lista in un jsonarray
		JSONArray lista = utenteTornato.getJSONArray(CostantiClient.KEY_LISTAUTENTI);
		System.out.println(lista.isEmpty());
		UtenteDto utenteRisultato = new UtenteDto();
		utenteRisultato.setEmail(utenteTornato.getString(CostantiClient.KEY_EMAIL));
		utenteRisultato.setPass(utenteTornato.getString(CostantiClient.KEY_PASSWORD));
		utenteRisultato.setImportoDaPagare(Integer.valueOf(utenteTornato.getInt(CostantiClient.KEY_IMPORTO)));
		utenteRisultato.setCodiceFiscale(utenteTornato.getString(CostantiClient.KEY_CODICEFISCALE));

		ArrayList<String> listaa = new ArrayList<String>();
		lista.forEach(element -> listaa.add(element.toString()));

		System.out.println(listaa.size());
		List<UtenteDto> utenti = new ArrayList<UtenteDto>();

		UtenteDto ute = new UtenteDto();
		ute.setCodiceFiscale(listaa.get(0));
		ute.setEmail(listaa.get(1));
		utenti.add(ute);

		utenteRisultato.setListaUtenti(utenti);
		return utenteRisultato;
	}

	// converto l'ogg in Json
	public String convertiObjectAdUnJson(UtenteDto utenteLoggato) {
		JSONObject utenteDaInviare = new JSONObject();
		utenteDaInviare.put(CostantiClient.KEY_EMAIL, utenteLoggato.getEmail());
		utenteDaInviare.put(CostantiClient.KEY_PASSWORD, utenteLoggato.getPass());
		utenteDaInviare.put(CostantiClient.KEY_IMPORTO, utenteLoggato.getImportoDaPagare());
		utenteDaInviare.put(CostantiClient.KEY_CODICEFISCALE, utenteLoggato.getCodiceFiscale());
		utenteDaInviare.put(CostantiClient.KEY_LISTAUTENTI, utenteLoggato.getListaUtenti());
		return utenteDaInviare.toString();
	}

	public String convertiRisposta(int responseCode, HttpURLConnection conn) throws IOException {

		// se la connessione ha avuto successo scriviamo cosa è tornato
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(CostantiClient.RISPOSTA);
		return response.toString();
	}

	public String convertiCredenzialiAdUnJson(UtenteDtoLogin utenteLoggato) {
		JSONObject utenteDaInviare = new JSONObject();
		utenteDaInviare.put(CostantiClient.KEY_EMAIL, utenteLoggato.getEmail());
		utenteDaInviare.put(CostantiClient.KEY_PASSWORD, utenteLoggato.getPass());
		return utenteDaInviare.toString();
	}

	public Utente convertiJsonUtenteAdObject(String json) {
		JSONObject utenteTornato = new JSONObject(json);

		Utente utenteRisultato = new Utente();
		utenteRisultato.setIdUtente(utenteTornato.getInt(CostantiClient.KEY_IDUTENTE));
		utenteRisultato.setCf(utenteTornato.getString(CostantiClient.KEY_CODICE_FISCALE));
		utenteRisultato.setNome(utenteTornato.getString(CostantiClient.KEY_NOME));
		utenteRisultato.setCognome(utenteTornato.getString(CostantiClient.KEY_COGNOME));
		utenteRisultato.setEmail(utenteTornato.getString(CostantiClient.KEY_EMAIL));
		utenteRisultato.setPass(utenteTornato.getString(CostantiClient.KEY_PASSWORD));
		utenteRisultato
				.setIdRuolo(utenteTornato.getJSONObject(CostantiClient.KEY_ID_RUOLO).getInt(CostantiClient.KEY_ID));
		utenteRisultato.setRuolo(new Ruolo());
		utenteRisultato.getRuolo()
				.setRuolo(utenteTornato.getJSONObject(CostantiClient.KEY_ID_RUOLO).getString(CostantiClient.KEY_RUOLO));
		utenteRisultato.getRuolo()
				.setIdRuolo(utenteTornato.getJSONObject(CostantiClient.KEY_ID_RUOLO).getInt(CostantiClient.KEY_ID));
		utenteRisultato.setSoldi(utenteTornato.getDouble(CostantiClient.KEY_SOLDI));

		return utenteRisultato;
	}
	// modo alternativo
	// json payload che dovro mandare al corpo della richiesta
	// String jsonPayload =
	// "{\"Email\":\""+utente.getEmail()+"\",\"Pass\":"+utente.getPass()+"}";
}
