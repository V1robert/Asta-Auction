package costanti;

import java.util.ArrayList;
import java.util.List;

public class CostantiPdflib {

	public static final String PDFLIB_APICI = "";
	public static final String PDFLIB_OPTLISTTAB1 = "fittextline={position={left center} font= ";
	public static final String PDFLIB_OPTLISTTAB2 = " fontsize=8} margin=4 colwidth=80";
	public static final String PDFLIB_OPTLIST_INIZIOPAGINA = "width=a4.width height=a4.height";
	public static final String PDFLIB_OPTLIST_FITTABLE = "stroke={{line=frame linewidth=0.8} {line=other linewidth=0.3}}";
	public static final String FONT_HELVETICA = "Helvetica";
	public static final String PDFLIB_GRUPPOFONT = "unicode";
	public static final String PDFLIB_BOLD_FONT_HELVETICA = "Helvetica-Bold";
	public static final String TXT_SIMBOLO_EURO = "€";
	public static final String PDFLIB_ASTA_RIEPILOGOFONT = "fontname=Helvetica-Bold fontsize=12";
	public static final String PDFLIB_HEADER_ASTA_DATASCADENZA = "data scadenza";
	public static final String PDFLIB_HEADER_ASTA_PREZZOVENDUTO = "prezzo venduto";
	public static final String PDFLIB_HEADER_ASTA_NOME_UTENTE = "nome utente";
	public static final String PDFLIB_HEADER_ASTA_IDOFFERTAPIUALTA = "id offerta piu alta";
	public static final String PDFLIB_HEADER_ASTA_NOMEARTICOLO = "nome articolo";
	public static final String PDFLIB_HEADER_ASTA_DESCRIZIONETAG = "descrizione tag";
	public static final String PDFLIB_SEARCHPATH = "searchpath={C:\\dev\\jars\\PDFlib-10.0.1-Windows-x64-java\\bind\\data\\NotoSerif-Regular}";
	public static final String PDFLIB_HEADER = "Riepilogo della lista di Aste";
	
	public static final String PDFLIB_USER_HOME = "user.home";
	public static final String PDFLIB_DESKTOP = "/Desktop/";
	
	public static final String PDFLIB_NOME_FILE = "astaRiepilogo.pdf";
	public static final String PDFLIB_URL_FOTO = "https://www.pdflib.com/fileadmin/cookbooks/PDFlib/PDFlib-Cookbook/input/kraxi_logo.tif";
	public static final String PDFLIB_PVF_IMAGE = "/pvf/image";
	public static final String PDFLIB_CREATEPVF = "createpvf source={url=";
	public static final String PDFLIB_TEXTALIGN = "fontname=Helvetica-Bold fontsize=24 encoding=unicode leading=100% charref";
	public static final String ERRORE = "Error:";
	public static final String TITLE = "Title";
	public static final String TITLE_RIEPILOGO = "riepilogo delle aste";
	public static final String CONTA_PAGINE = "pagina";
	public static final String AUTO = "auto";
	public static final String GRAFFA = "}";
	
	
	@SuppressWarnings("serial")
	public static final ArrayList<String> PDFLIB_HEADERS_ASTA = new ArrayList<String>() {
		{
			add("data scadenza");
			add(PDFLIB_HEADER_ASTA_PREZZOVENDUTO);
			add(PDFLIB_HEADER_ASTA_NOME_UTENTE);
			add(PDFLIB_HEADER_ASTA_IDOFFERTAPIUALTA);
			add(PDFLIB_HEADER_ASTA_NOMEARTICOLO);
			add(PDFLIB_HEADER_ASTA_DESCRIZIONETAG);

		}
	};
}
