package costanti;

public class CostantiClient {

	public final static String URL_DELLA_BANCA="http://localhost:8080/BancaREST/rest/utenteRest/provaAsta";
	public final static String URL_DEL_LOGIN_SPRING="http://localhost:8081/UtenteRest/findByEmailAndPassword";
	public final static String CONTENT_TYPE="Content-Type";
	public final static String APPLICATION_JSON="application/json";
	public final static String RISPOSTA="Risposta dall rest";
	public final static String RISPOSTA_ERRORE="Richiesta fallita con codice :";
	public final static String HTTP_POST="POST";
	public final static String HTTP_GET="GET";
	

	public final static String KEY_ID="id";  //id utente della per la banca
	public final static String KEY_IDUTENTE="idUtente"; // risposta da spring
	public final static String KEY_CODICE_FISCALE="cf";
	public final static String KEY_NOME="nome";
	public final static String KEY_COGNOME="cognome";
	public final static String KEY_EMAIL="email";
    public final static String KEY_PASSWORD="pass";
    public final static String KEY_ID_RUOLO="idRuolo";
    public final static String KEY_RUOLO="ruolo";
    public final static String KEY_SOLDI="soldi";
    public final static String KEY_IMPORTO="importoDaPagare";
    public final static String KEY_CODICEFISCALE="codiceFiscale";
    public final static String KEY_LISTAUTENTI="listaUtenti";
}
