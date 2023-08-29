package pdfUtils;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import models.Articolo;
import models.Asta;
import models.Tag;
import models.Utente;
import costanti.CostantiPdf;

public class SendPDF {

	public byte[] creaPdf(Asta asta, Utente utenteLoggato, Boolean sceltaPdf) throws Exception {

		// creation of the document with a certain size and certain margins
		// (you can use PageSize.Letter instead of PageSize.A4)

		Document document = new Document(PageSize.A4, CostantiPdf.GRANDEZZA_FOGLIO_A4, CostantiPdf.GRANDEZZA_FOGLIO_A4,
				CostantiPdf.GRANDEZZA_FOGLIO_A4, CostantiPdf.GRANDEZZA_FOGLIO_A4);

		FileOutputStream pdfOutputFile = new FileOutputStream(
				"C://Users//RobertVM//.eclipse//workspace2023//pdf/proviamo.pdf");

		ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();

		final PdfWriter pdfWriter = PdfWriter.getInstance(document, sceltaPdf ? outPutStream : pdfOutputFile);

		try {
			// creation of the different writers

			// various fonts
			final BaseFont baseFontTimes = BaseFont.createFont(BaseFont.TIMES_ROMAN, CostantiPdf.FONT_CP, false);
			final BaseFont baseFontCourier = BaseFont.createFont(BaseFont.COURIER, CostantiPdf.FONT_CP, false);

			// headers and footers must be added before the document is opened
			document.setFooter(creaFooter(baseFontCourier));
			document.setHeader(creaHeader(baseFontTimes));

			document.open();
			// apro il documento questo sara la prima pagina

			document.newPage();

			// creo la data di oggi , stara sopra a destra del documento

			document.add(creaDataDiOggi());

			// creo un paragrafo dove do info all ricevente della mail

			document.add(creaBenvenutoDocumentoPdfUtente());

			// aggiungo la foto di duke

			document.add(aggiungiFoto());

			// creo il paragrafo con il nome dell utente piu la lista da lui richiesta

			document.add(creaParagrafoUtente(utenteLoggato));

			ListePdf astaVinta = new ListePdf();
			astaVinta.getListaAste().add(asta);
			document.add(creaTable(listaDiAste(astaVinta.getListaAste(), document), CostantiPdf.ASTA,
					CostantiPdf.GRANDEZZA_TABLE_ASTA));

			System.out.println(sceltaPdf);
			// we're done!
			document.close();

			pdfOutputFile.close();
			pdfWriter.close();
			return sceltaPdf ? outPutStream.toByteArray() : null;
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		throw new Exception();
	}

	@SuppressWarnings("unchecked")
	public byte[] creaPdfAdmin(HashMap<Integer, Object> listaDiListe, Utente utenteLoggato, Boolean sceltaPdf) throws Exception {

		// creation of the document with a certain size and certain margins
		// (you can use PageSize.Letter instead of PageSize.A4)

		Document document = new Document(PageSize.A4, CostantiPdf.GRANDEZZA_FOGLIO_A4, CostantiPdf.GRANDEZZA_FOGLIO_A4,
				CostantiPdf.GRANDEZZA_FOGLIO_A4, CostantiPdf.GRANDEZZA_FOGLIO_A4);
		ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();

		FileOutputStream pdfOutputFile = new FileOutputStream(
				"C://Users//RobertVM//.eclipse//workspace2023//pdf/proviamo.pdf");

		final PdfWriter pdfWriter = PdfWriter.getInstance(document, sceltaPdf ? outPutStream  : pdfOutputFile);
		try {
			// creation of the different writers

			// various fonts
			final BaseFont baseFontTimes = BaseFont.createFont(BaseFont.TIMES_ROMAN, CostantiPdf.FONT_CP, false);
			final BaseFont baseFontCourier = BaseFont.createFont(BaseFont.COURIER, CostantiPdf.FONT_CP, false);

			// headers and footers must be added before the document is opened
			document.setFooter(creaFooter(baseFontCourier));
			document.setHeader(creaHeader(baseFontTimes));

			document.open();
			// apro il documento questo sara la prima pagina

			document.newPage();

			// creo la data di oggi , stara sopra a destra del documento

			document.add(creaDataDiOggi());

			// creo un paragrafo dove do info all ricevente della mail

			document.add(creaBenvenutoDocumentoPdf());

			// aggiungo la foto di duke

			document.add(aggiungiFoto());

			// creo il paragrafo con il nome dell utente piu la lista da lui richiesta

			document.add(creaParagrafoConListeRichieste(utenteLoggato));

			if (listaDiListe.containsKey(4)) {
				document.add(
						creaTable(listaDiTag((List<Tag>) listaDiListe.get(4) , document), CostantiPdf.TAG, CostantiPdf.GRANDEZZA_TABLE_TAG));
				document.newPage();
			}
			if (listaDiListe.containsKey(2)) {
				document.add(creaTable(listaDiArticolo((List<Articolo>) listaDiListe.get(2) , document), CostantiPdf.ARTICOLO,
						CostantiPdf.GRANDEZZA_TABLE_ARTICOLO));
				document.newPage();
			}

			if (listaDiListe.containsKey(3)) {
				document.add(creaTable(listaDiUtenti((List<Utente>) listaDiListe.get(3) , document), CostantiPdf.UTENTE,
						CostantiPdf.GRANDEZZA_TABLE_UTENTE));
				document.newPage();
			}
			if (listaDiListe.containsKey(1)) {
				document.add(creaTable(listaDiAste((List<Asta>) listaDiListe.get(1), document), CostantiPdf.ASTA,
						CostantiPdf.GRANDEZZA_TABLE_ASTA));
			}

			// we're done!
			document.close();

			pdfOutputFile.close();
			pdfWriter.close();
			System.out.println(sceltaPdf);
			return sceltaPdf ? outPutStream.toByteArray() : null ;
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		throw new Exception();
	}

	public Table creaTable(List<String> listaValori, String tipoTabella, Integer grandezzaTabella) {

		Table table = new Table(grandezzaTabella);
		table.setCellsFitPage(true);

		table.setBorderWidth(2);
		table.setBorderColor(Color.BLACK);
		table.setPadding(5);
		table.setSpacing(0);

		// 120 width per toccare i bordi
		table.setWidth(110);

		// aggiungo il contaRighe

		// table.addCell(creaContaRigheCella());

		// ciclo per aggiungere 6 headers
		List<String> listaCostanti = new ArrayList<String>();
		listaCostanti = inserisciCostantiAsta(tipoTabella);
		for (int y = 0; y < listaCostanti.size(); y++) {
			String valoreGrande = listaCostanti.get(y);

			table.addCell(creaCelleHeaders(valoreGrande));
		}

		table.endHeaders();

		// numero di righe
		/*
		 * for (int y = 0; y < listaValori.size(); y++) {
		 * table.addCell(inserisciValoriNelleCelle(y)); }
		 */

		// valore delle celle
		for (int x = 0; x < listaValori.size(); x++) {
			String valore = listaValori.get(x);

			table.addCell(inserisciValoriNelleCelle(valore));
		}

		return table;
	}

	public List<String> listaDiArticolo(List<Articolo> listaArticolo, Document document) {
		document.add(creaNomeHeaderTabella(CostantiPdf.ARTICOLO));

		List<String> lista = new ArrayList<String>();
		for (int x = 0; x < listaArticolo.size(); x++) {
			lista.add(listaArticolo.get(x).getNomeArticolo());
			lista.add(listaArticolo.get(x).getDesc());
		}
		return lista;
	}

	public List<String> listaDiAste(List<Asta> listaAste, Document document) {
		document.add(creaNomeHeaderTabella(CostantiPdf.ASTA));

		List<String> lista = new ArrayList<String>();
		for (int x = 0; x < listaAste.size(); x++) {
			lista.add(String.valueOf(listaAste.get(x).getDataScadenza()));
			lista.add(String.valueOf(listaAste.get(x).getPrezzoVenduto()));
			lista.add(listaAste.get(x).getUtente().getNome());
			lista.add(String.valueOf(listaAste.get(x).getIdOffertaPiuAlta()));
			lista.add(listaAste.get(x).getArticolo().getNomeArticolo());
			lista.add(listaAste.get(x).getTag().getDescrizioneTag());
		}
		return lista;
	}

	public List<String> listaDiUtenti(List<Utente> listaUtenti, Document document) {
		document.add(creaNomeHeaderTabella(CostantiPdf.UTENTE));

		List<String> lista = new ArrayList<String>();
		for (int x = 0; x < listaUtenti.size(); x++) {
			lista.add(listaUtenti.get(x).getCf());
			lista.add(listaUtenti.get(x).getNome());
			lista.add(listaUtenti.get(x).getCognome());
			lista.add(listaUtenti.get(x).getEmail());
			lista.add(String.valueOf(listaUtenti.get(x).getSoldi()));
		}
		return lista;
	}

	public List<String> listaDiTag(List<Tag> listaTag, Document document) {

		document.add(creaNomeHeaderTabella(CostantiPdf.TAG));

		System.out.println(listaTag);

		List<String> lista = new ArrayList<String>();
		for (int x = 0; x < listaTag.size(); x++) {
			lista.add(listaTag.get(x).getDescrizioneTag());
		}

		return lista;
	}

	public Paragraph creaBenvenutoDocumentoPdf() {
		Paragraph paragrafo = new Paragraph(CostantiPdf.INFO_BENVENUTO);
		paragrafo.getFont().setStyle(Font.BOLD);
		paragrafo.setSpacingAfter(2);
		return paragrafo;

	}

	public Paragraph creaBenvenutoDocumentoPdfUtente() {
		Paragraph paragrafo = new Paragraph(CostantiPdf.INFO_CONGRATULAZIONI);
		paragrafo.getFont().setStyle(Font.BOLD);
		paragrafo.setSpacingAfter(2);
		return paragrafo;

	}

	public Image aggiungiFoto() throws BadElementException, IOException {

		Image fotoDuke = Image.getInstance(CostantiPdf.LINK_DI_DUKE);
		fotoDuke.setAbsolutePosition(500, 700);
		fotoDuke.scalePercent(20);

		return fotoDuke;

	}

	public Paragraph creaParagrafoConListeRichieste(Utente utenteLoggato) {
		Paragraph paragrafo = new Paragraph(CostantiPdf.LISTE_RICHIESTE + " " + utenteLoggato.getNome());
		paragrafo.getFont().setStyle(Font.ITALIC);
		return paragrafo;
	}

	public Paragraph creaParagrafoUtente(Utente utenteLoggato) {
		Paragraph paragrafo = new Paragraph(CostantiPdf.BENVENUTO_UTENTE + " " + utenteLoggato.getNome());
		paragrafo.getFont().setStyle(Font.ITALIC);
		return paragrafo;
	}

	public Paragraph creaDataDiOggi() {
		Date dataDiOggi = new Date();
		Paragraph paragrafo = new Paragraph(CostantiPdf.DATA_CREAZIONE + dataDiOggi);
		paragrafo.getFont().setStyle(Font.ITALIC);
		paragrafo.setAlignment(Element.ALIGN_RIGHT);
		return paragrafo;
	}

	public HeaderFooter creaFooter(BaseFont baseFontCourier) {
		HeaderFooter footer = new HeaderFooter(new Phrase(CostantiPdf.CONTA_PAGINE, new Font(baseFontCourier)), true);
		footer.setBorder(Rectangle.NO_BORDER);
		footer.setAlignment(Element.ALIGN_CENTER);
		return footer;
	}

	public HeaderFooter creaHeader(BaseFont baseFontTimes) {
		HeaderFooter header = new HeaderFooter(new Phrase(CostantiPdf.LISTA_HEADER, new Font(baseFontTimes)), false);
		header.setAlignment(Element.ALIGN_CENTER);
		header.setBackgroundColor(Color.RED);
		header.setBorderColor(Color.RED);
		header.setBorderWidthLeft(120);
		header.setBorderWidthRight(120);
		return header;
	}

	public Cell inserisciValoriNelleCelle(String listaValori) {

		Cell cella = new Cell(listaValori);
		cella.setRowspan(2);
		cella.setColspan(3);
		cella.setBorderColor(Color.BLACK);
		cella.setHorizontalAlignment(HorizontalAlignment.CENTER);
		System.out.println("valore" + listaValori);
		return cella;

	}

	public Cell inserisciValoriNelleCelle(Integer contaRighe) {

		Cell cella = new Cell(String.valueOf(contaRighe));
		cella.setRowspan(2);
		cella.setColspan(1);
		cella.setBorderColor(Color.BLACK);
		cella.setHorizontalAlignment(HorizontalAlignment.CENTER);
		System.out.println("conta righe");
		return cella;

	}

	public <T> List<String> inserisciCostantiAsta(String tipoTabella) {
		List<String> arrayDeiHeaders = new ArrayList<String>();
		System.out.println(tipoTabella);
		if (tipoTabella.equalsIgnoreCase(CostantiPdf.ASTA)) {
			arrayDeiHeaders.add(CostantiPdf.ASTA_DATA_SCADENZA);
			arrayDeiHeaders.add(CostantiPdf.ASTA_PREZZO_VENDUTO);
			arrayDeiHeaders.add(CostantiPdf.ASTA_NOME_PROPIETARIO);
			arrayDeiHeaders.add(CostantiPdf.ASTA_OFFERTA_PIU_ALTA);
			arrayDeiHeaders.add(CostantiPdf.ASTA_ARTICOLO);
			arrayDeiHeaders.add(CostantiPdf.ASTA_TAG);

			System.out.println("sono entrato");

		} else if (tipoTabella.equalsIgnoreCase(CostantiPdf.UTENTE)) {
			arrayDeiHeaders.add(CostantiPdf.UTENTE_CODICEFISCALE);
			arrayDeiHeaders.add(CostantiPdf.UTENTE_NOME);
			arrayDeiHeaders.add(CostantiPdf.UTENTE_COGNOME);
			arrayDeiHeaders.add(CostantiPdf.UTENTE_EMAIL);
			arrayDeiHeaders.add(CostantiPdf.UTENTE_SOLDI);

		} else if (tipoTabella.equalsIgnoreCase(CostantiPdf.ARTICOLO)) {
			arrayDeiHeaders.add(CostantiPdf.ARTICOLO_DESCRIZIONE);
			arrayDeiHeaders.add(CostantiPdf.ARTICOLO_NOME_DEL_ARTICOLO);

		} else if (tipoTabella.equalsIgnoreCase(CostantiPdf.TAG)) {

			arrayDeiHeaders.add(CostantiPdf.TAG_DESCRIZIONE);

		}
		System.out.println(arrayDeiHeaders);
		return arrayDeiHeaders;

	}

	public Cell creaCelleHeaders(String tipoTabella) {

		Cell cella = new Cell(tipoTabella);
		cella.setHorizontalAlignment(HorizontalAlignment.CENTER);
		cella.setRowspan(2);
		cella.setHeader(true);
		cella.setColspan(3);
		cella.setBorderColor(Color.CYAN);
		return cella;

	}

	public Cell creaContaRigheCella() {
		Paragraph paragrafo = new Paragraph(CostantiPdf.NUMERO_RIGHE, new Font(Font.BOLD));

		Cell cella = new Cell(paragrafo);
		cella.setHorizontalAlignment(HorizontalAlignment.CENTER);
		cella.setHeader(true);
		cella.setRowspan(2);
		cella.setColspan(1);
		cella.setBorderColor(Color.CYAN);
		return cella;

	}

	public Paragraph creaNomeHeaderTabella(String tipoHeader) {
		String titolo = "";
		if (tipoHeader.equalsIgnoreCase(CostantiPdf.ASTA)) {
			titolo = CostantiPdf.TITOLO_ASTA;
		} else if (tipoHeader.equalsIgnoreCase(CostantiPdf.UTENTE)) {
			titolo = CostantiPdf.TITOLO_UTENTI;
		} else if (tipoHeader.equalsIgnoreCase(CostantiPdf.ARTICOLO)) {
			titolo = CostantiPdf.TITOLO_ARTICOLO;
		} else if (tipoHeader.equalsIgnoreCase(CostantiPdf.TAG)) {
			titolo = CostantiPdf.TITOLO_TAG;
		}
		Paragraph paragrafo = new Paragraph(titolo, new Font(Font.BOLD));
		paragrafo.getFont().setStyle(Font.BOLD);
		paragrafo.setAlignment(Element.ALIGN_CENTER);

		return paragrafo;
	}
}
