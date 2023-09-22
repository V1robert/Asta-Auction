package costanti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CostantiArchivio {

	public static final String EXCEPTION_ARCHIVIO = "il file inviato non è un archivio o non è un archivio compatibile";
	public static final String VERSIONE_RAR_NON_SUPPORTATA = "526172211A0701";
	public static final String EXCEPTION_VERSIONE_RAR = "errore versione file rar 5.0.0 non supportata";
	public static final String MAGIC_NUMBER_RAR = "526172211A0700";
	public static final String MAGIC_NUMBER_ZIP = "504B0304";
	public static final String FILE_ZIP = "file zip";
	public static final String MAGIC_NUMBER_7_ZIP = "377ABCAF271C";
	public static final String FILE_7_ZIP = "file 7zip";

	public static final int VALORE_FILE_RAR = 1;
	public static final int VALORE_FILE_ZIP = 2;
	public static final int VALORE_FILE_7ZIP = 3;
	public static final int VALORE_FILE_ERRATO = 4;

	public static final String MAGIC_NUMBER_PDF = "255044462D";

	public static final String MAGIC_NUMBER_SPANNED_ARCHIVE = "504B0304";

	public static final String MAGIC_NUMBER_DOCX = "504B0506";
	public static final String MAGIC_NUMBER_XLS_EXCELL = "D0CF11E0A1B11AE1";
	public static final String STRING_FORMAT = "%02X";
	public static final String MAGIC_NUMBER_NON_TROVATO = "il file all interno non è di un formato supportato";
	public static final String EXCEPTION_GRANDEZZA_FILE = "Grandezza file sconosciuta";
	public static final String ERRORE_NEL_CONVERTIRE_INPUT_A_BYTEARRAY = "errore nel metodo convertiInputStreamAByteArray";

	@SuppressWarnings("serial")
	public static final List<String> LISTA_MAGIC_NUMBER = Collections.unmodifiableList(new ArrayList<String>() {
		{
			// add("526172211A0700");//rar
			// add("504B0304");//zip
			// add("377ABCAF271C");//7zip
			add("255044462D"); // pdf
			add("504B0304"); // xls o docx
			add("504B0506"); // empty archive xls
			add("504B0708"); // spanned archive
			add("D0CF11E0A1B11AE1");// excell
			add("FFD8FFE0");// jpg
			add("FFD8FFE000104A4649460001");// jpg
			add("FFD8FFEE");// jpg
			add("FFD8FFE1????457869660000");// jpg
			add("FFD8FFE0");// jpg
			add("89504E470D0A1A0A");// png
		}
	});

}
