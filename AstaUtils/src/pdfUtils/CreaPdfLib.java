package pdfUtils;

import java.util.List;

import com.pdflib.PDFlibException;
import com.pdflib.pdflib;

import costanti.CostantiPdflib;
import filesUtils.LeggiFile;


public class CreaPdfLib {

	public void creaPdfRiepilogoAsta(List<String> valori) throws Exception {
		String desktopPath = System.getProperty(CostantiPdflib.PDFLIB_USER_HOME) + CostantiPdflib.PDFLIB_DESKTOP;
		String pdfFilePath = desktopPath + CostantiPdflib.PDFLIB_NOME_FILE;
LeggiFile fileDelCliente = new LeggiFile();
		List<String> dati = fileDelCliente.vediFile();
		
		final double llx = 100, lly = -50, urx = Double.valueOf(dati.get(1)), ury = Double.valueOf(dati.get(2));

		int image, graphics, tf = -1, table = -1;
		//int row = 1;
		//int col = 1;
		//final int rowmax = 50, colmax = 4;
		//int exitcode = 0;
		final double x = 270, y = 30;
		String contaPagine = " 1";
		//String result;

		//String optlist;

		String headertext = CostantiPdflib.PDFLIB_HEADER;
		// String optlist = "fontname={NotoSerif-Regular} fontsize=24 ";

		String searchpath = CostantiPdflib.PDFLIB_SEARCHPATH;

		try {
			pdflib document = new pdflib();
			document.set_option(searchpath); // punto alla cartella pdflib
			if (document.begin_document(pdfFilePath, CostantiPdflib.PDFLIB_APICI) == -1) {
				throw new Exception(CostantiPdflib.ERRORE + document.get_errmsg());
			}
			// setto tabella

			table = creaTab(valori, document, table);
			table = creaHeaderAsta(document, table);

			document.set_info(CostantiPdflib.TITLE, dati.get(0));
			document.begin_page_ext(0, 0, CostantiPdflib.PDFLIB_OPTLIST_INIZIOPAGINA); // apro pagina

			document.fit_textline(CostantiPdflib.CONTA_PAGINE + contaPagine, x, y,
					CostantiPdflib.PDFLIB_ASTA_RIEPILOGOFONT);

			tf = document.add_textflow(tf, headertext, CostantiPdflib.PDFLIB_TEXTALIGN);
			document.fit_textflow(tf, 200, 100, 600, 800, CostantiPdflib.PDFLIB_APICI);
			document.fit_image(caricaFoto(document), 50, 750, CostantiPdflib.PDFLIB_APICI);
			document.fit_table(table, llx, lly, urx, ury, CostantiPdflib.PDFLIB_OPTLIST_FITTABLE); // posiziono tabella

			document.end_page_ext(CostantiPdflib.PDFLIB_APICI); // chiudo pagina
			document.delete_pvf(CostantiPdflib.PDFLIB_PVF_IMAGE);
			document.end_document(CostantiPdflib.PDFLIB_APICI); // chiudo documento
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int creaTab(List<String> valori, pdflib document, int table) {
		int row = 2, col = 1;
		try {

			for (int x = 0; x < valori.size(); x++) {

				table = document.add_table_cell(table, col, row, valori.get(x),
						CostantiPdflib.PDFLIB_OPTLISTTAB1
								+ document.load_font(CostantiPdflib.PDFLIB_BOLD_FONT_HELVETICA,
										CostantiPdflib.PDFLIB_GRUPPOFONT, CostantiPdflib.PDFLIB_APICI)
								+ CostantiPdflib.PDFLIB_OPTLISTTAB2);

				col++;
				if (x != 0) {
					if (x % 6 == 0) {
						row++;
						col = 1;
					}
				}

			}

		} catch (PDFlibException e) {
			e.printStackTrace();
		}
		return table;
	}

	public int creaHeaderAsta(pdflib document, int table) {
		int row = 1, col = 1;
		try {

			for (int y = 0; y < 6; y++) {
				table = document.add_table_cell(table, col, row, CostantiPdflib.PDFLIB_HEADERS_ASTA.get(y),
						CostantiPdflib.PDFLIB_OPTLISTTAB1
								+ document.load_font(CostantiPdflib.PDFLIB_BOLD_FONT_HELVETICA,
										CostantiPdflib.PDFLIB_GRUPPOFONT, CostantiPdflib.PDFLIB_APICI)
								+ CostantiPdflib.PDFLIB_OPTLISTTAB2);
				col++;

			}
		} catch (PDFlibException e) {
			e.printStackTrace();
		}
		return table;

	}

	public int caricaFoto(pdflib document) throws Exception {
		final String image_url = CostantiPdflib.PDFLIB_URL_FOTO;
		// Download image data from URL and store it in a PVF file
		if (document.download(CostantiPdflib.PDFLIB_PVF_IMAGE,
				CostantiPdflib.PDFLIB_CREATEPVF + image_url + CostantiPdflib.GRAFFA) == -1) {
			throw new Exception(CostantiPdflib.ERRORE + document.get_errmsg());
		}
		// Load the image from the PVF
		int image = document.load_image(CostantiPdflib.AUTO, CostantiPdflib.PDFLIB_PVF_IMAGE,
				CostantiPdflib.PDFLIB_APICI);
		if (image == -1) {
			throw new Exception(CostantiPdflib.ERRORE + document.get_errmsg());
		}
		return image;
	}
}
