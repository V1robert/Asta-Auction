package inviapdf;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

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
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

import models.Articolo;
import models.Asta;
import models.Tag;
import models.Utente;

public class SendEmailPDF {

	public void inviaEmailAdmin(List<Articolo> listaArticoli, List<Asta> listaAste, List<Tag> listaTag,
			List<Utente> listaUtenti) {

		String host = "smtp.gmail.com";
		final String user = "provajavaemailexolab@gmail.com";// change accordingly
		final String password = "megbtzwvgqsqlnyk";// change accordingly

		String to = "provajavaemailexolab@gmail.com";// change accordingly

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("this is the result of the asta you won");
			message.setText("This is simple program of sending email using JavaMail API");

			// construct the text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(message.getSubject());

			// now write the PDF content to the output stream

			byte[] bytes = creaPdfAdmin(listaArticoli, listaAste, listaTag, listaUtenti);

			// construct the pdf body part
			ByteArrayDataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("astavinta.pdf");

			// construct the mime multi part
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			message.setContent(mimeMultipart);
			Transport.send(message);

			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public byte[] creaPdfAdmin(List<Articolo> listaArticoli, List<Asta> listaAste, List<Tag> listaTag,
			List<Utente> listaUtenti) {

		// creation of the document with a certain size and certain margins
		// (you can use PageSize.Letter instead of PageSize.A4)

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outPutStream);
		try {
			// creation of the different writers
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("OpenPDFExample.pdf"));

			// various fonts
			BaseFont bf_helv = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false);
			BaseFont bf_times = BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1252", false);
			BaseFont bf_courier = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false);
			BaseFont bf_symbol = BaseFont.createFont(BaseFont.SYMBOL, "Cp1252", false);

			// headers and footers must be added before the document is opened
			HeaderFooter footer = new HeaderFooter(new Phrase("This is page: ", new Font(bf_courier)), true);
			footer.setBorder(Rectangle.NO_BORDER);
			footer.setAlignment(Element.ALIGN_CENTER);
			document.setFooter(footer);

			HeaderFooter header = new HeaderFooter(
					new Phrase("Liste richieste come:Asta,Prodotto,Tag,Utenti", new Font(bf_times)), false);
			header.setAlignment(Element.ALIGN_CENTER);
			header.setBackgroundColor(Color.RED);
			header.setBorderColor(Color.RED);
			header.setBorderWidthLeft(120);
			header.setBorderWidthRight(120);
			document.setHeader(header);

			document.open();

			int y_line1 = 650;
			int y_line2 = y_line1 - 50;
			int y_line3 = y_line2 - 50;

			// draw a few lines ...
			PdfContentByte cb = writer.getDirectContent();
			cb.setLineWidth(0f);
			cb.moveTo(250, y_line3 - 100);
			cb.lineTo(250, y_line1 + 100);
			cb.moveTo(50, y_line1);
			cb.lineTo(400, y_line1);
			cb.moveTo(50, y_line2);
			cb.lineTo(400, y_line2);
			cb.moveTo(50, y_line3);
			cb.lineTo(400, y_line3);
			cb.stroke();
			// ... and some text that is aligned in various ways
			cb.beginText();
			cb.setFontAndSize(bf_helv, 12);
			String text = "Sample text for alignment";
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text + " Center", 250, y_line1, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, text + " Right", 250, y_line2, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, text + " Left", 250, y_line3, 0);
			cb.endText();

			// add an image, scale it down by half, and put at an absolute position
			/*
			 * try { Image simple =
			 * Image.getInstance("C:/dev/Duke_(Java_mascot)_waving.svg.png");
			 * simple.setAbsolutePosition(500,750); simple.scalePercent(50);
			 */

			/*
			 * simple.setBorderColorBottom(Color.blue);
			 * simple.setBorderColorLeft(Color.CYAN);
			 * simple.setBorderColorRight(Color.GREEN);
			 * simple.setBorderColorTop(Color.PINK);
			 */

			/*
			 * document.add(simple); } catch (Exception ex) {
			 * System.out.println("no image for you"); }
			 */

			// start second page
			document.newPage();

			// add text in three paragraphs from top to bottom with various font styles
			Date dataDiOggi=new Date();
			Paragraph par = new Paragraph("data di creazione del documento "+dataDiOggi);
			par.getFont().setStyle(Font.ITALIC);
			par.setAlignment(Element.ALIGN_RIGHT);
			document.add(par);
			
			par = new Paragraph("bla bla bla bla bla bla bla bla");
			par.getFont().setStyle(Font.BOLD);
			par.setSpacingAfter(2);
			document.add(par);
			par = new Paragraph("Tutte le liste da lei richieste "+listaUtenti.get(0).getNome());
			par.getFont().setStyle(Font.ITALIC);
			
			document.add(par);
			
			

			// demonstrate some table features
			Table table = new Table(14);
			table.setCellsFitPage(true);
			// 2 pixel wide blue border

			try {
				Image simple = Image.getInstance("C:/dev/Duke_(Java_mascot)_waving.svg.png");
				simple.setAbsolutePosition(500, 700);
				simple.scalePercent(20);
				// simple.scaleToFit(PageSize.A4);
				/*
				 * simple.setBorderColorBottom(Color.blue);
				 * simple.setBorderColorLeft(Color.CYAN);
				 * simple.setBorderColorRight(Color.GREEN);
				 * simple.setBorderColorTop(Color.PINK);
				 */

				document.add(simple);
			} catch (Exception ex) {
				System.out.println("no image for you");
			}
			table.setBorderWidth(2);
			table.setBorderColor(new Color(0, 0, 255));
			table.setPadding(5);
			table.setSpacing(0);
			
			//120 width per toccare i bordi
			table.setWidth(110);
			
			
			
			

			if (listaAste.size()>0) {

				/*Cell c = new Cell("Liste di tutte le aste");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setHeader(true);
				c.setRowspan(3);
				c.setColspan(13);
				//c.setUseBorderPadding(true);
				table.addCell(c);
				table.endHeaders();*/

				// le prime 7 colonne sopra
				par = new Paragraph("LISTA DELLE ASTE");
				par.getFont().setStyle(Font.BOLD);
				par.setAlignment(Element.ALIGN_CENTER);
				document.add(par);

				Cell c = new Cell("N righe");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setHeader(true);
				c.setRowspan(2);
				c.setColspan(1);
				c.setBorderColor(Color.CYAN);
				table.addCell(c);
				
				c = new Cell("data di scadenza");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(2);
				c.setHeader(true);
				c.setColspan(2);
				c.setBorderColor(Color.CYAN);
				table.addCell(c);
				
				c = new Cell("prezzo venduto");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(2);
				c.setHeader(true);
				c.setColspan(2);
				c.setBorderColor(new Color(255, 0, 0));
				table.addCell(c);
				c = new Cell("nome propietario");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(2);
				c.setHeader(true);
				c.setColspan(3);
				c.setBorderColor(Color.green);
				table.addCell(c);
				c = new Cell("offerta piu alta");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(2);
				c.setHeader(true);
				c.setColspan(2);
				c.setBorderColor(Color.magenta);
				table.addCell(c);
				c = new Cell("articolo all asta");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(2);
				c.setHeader(true);
				c.setColspan(2);
				c.setBorderColor(Color.RED);
				table.addCell(c);
				c = new Cell("tag del asta");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(2);
				c.setHeader(true);
				c.setColspan(2);
				c.setBorderColor(Color.yellow);
				table.addCell(c);
				
				table.endHeaders();

				// inizio a ciclare l'array con tutte le aste disponibili i loro valori andranno
				// nelle celle sotto

				for (int x = 0; x < listaAste.size(); x++) {

					//conta righe
					c = new Cell(String.valueOf(x));
					c.setRowspan(2);
					c.setColspan(1);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					table.addCell(c);
					
					
					c = new Cell(String.valueOf(listaAste.get(x).getDataScadenza()));
					c.setRowspan(2);
					c.setColspan(2);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					table.addCell(c);

					c = new Cell(String.valueOf(listaAste.get(x).getPrezzoVenduto()));
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					c.setRowspan(2);
					c.setColspan(2);
					c.setBorderColor(new Color(255, 0, 0));
					table.addCell(c);

					c = new Cell(listaAste.get(x).getUtente().getNome());
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					c.setRowspan(2);
					c.setColspan(3);
					c.setBorderColor(Color.green);
					table.addCell(c);

					c = new Cell(String.valueOf(listaAste.get(x).getOfferta().getSoldiOfferti()));
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					c.setBorderColor(Color.magenta);
					c.setRowspan(2);
					c.setColspan(2);
					table.addCell(c);

					c = new Cell(listaAste.get(x).getArticolo().getNome_articolo());
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					c.setRowspan(2);
					c.setColspan(2);
					c.setBorderColor(Color.RED);
					table.addCell(c);

					c = new Cell(listaAste.get(x).getTag().getDescrizioneTag());
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					c.setRowspan(2);
					c.setColspan(2);
					c.setBorderColor(Color.yellow);
					table.addCell(c);

				}
			}
			document.add(table);

			//start another page
			

			if(listaArticoli.size()>0) {
				
				document.newPage();
				
				Table tablee =new Table(7);
				tablee.setCellsFitPage(true);
				tablee.setBorderWidth(2);
				tablee.setBorderColor(Color.magenta);
				tablee.setPadding(5);
				tablee.setSpacing(0);
				tablee.setWidth(110);
				
				
				
				/*Cell c = new Cell("Liste di tutti gli Articoli");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setHeader(true);
				c.setRowspan(3);
				c.setColspan(7);
				tablee.addCell(c);*/
				
				
				
				// le prime tre celle sopra
				par = new Paragraph("LISTA DEGLI ARTICOLI");
				par.getFont().setStyle(Font.BOLD);
				par.setAlignment(Element.ALIGN_CENTER);
				document.add(par);
				
				Cell c = new Cell("N righe");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(1);
				c.setHeader(true);
				c.setColspan(1);
				c.setBorderColor(Color.CYAN);
				tablee.addCell(c);
				
				c = new Cell("Nome del articolo");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(1);
				c.setHeader(true);
				c.setColspan(3);
				c.setBorderColor(Color.CYAN);
				tablee.addCell(c);
				c = new Cell("Descrizione del articolo");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(1);
				c.setHeader(true);
				c.setColspan(3);
				c.setBorderColor(new Color(255, 0, 0));
				tablee.addCell(c);
				tablee.endHeaders();
				for (int x = 0; x < listaArticoli.size(); x++) {

					//conta righe
					c = new Cell(String.valueOf(x));
					c.setRowspan(2);
					c.setColspan(1);
					c.isUseBorderPadding();
					c.setBorderColor(Color.CYAN);
				
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tablee.addCell(c);
					
					c = new Cell(String.valueOf(listaArticoli.get(x).getNome_articolo()));
					c.setRowspan(2);
					c.setColspan(3);
					c.isUseBorderPadding();
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tablee.addCell(c);

					c = new Cell(String.valueOf(listaArticoli.get(x).getDesc()));
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					c.setRowspan(2);
					c.setColspan(3);
					c.setUseBorderPadding(true);
					c.setBorderColor(new Color(255, 0, 0));
					tablee.addCell(c);

				}
				document.add(tablee);
			}
			
			
			
			

			if(listaTag.size()>0) {
				
				document.newPage();
				
				Table tableTag =new Table(4);
				tableTag.setBorderWidth(2);
				tableTag.setCellsFitPage(true);
				tableTag.setBorderColor(Color.black);
				tableTag.setPadding(5);
				tableTag.setSpacing(0);
				tableTag.setWidth(110);
			
				
				
				/*Cell c = new Cell("Liste di tutti i Tag");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setHeader(true);
				c.setRowspan(3);
				c.setColspan(4);
				tableTag.addCell(c);*/
				
				
				
				// le prime due celle sopra
				par = new Paragraph("LISTA DEI TAG");
				par.getFont().setStyle(Font.BOLD);
				par.setAlignment(Element.ALIGN_CENTER);
				document.add(par);
				
				Cell c = new Cell("N righe");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(1);
				c.setHeader(true);
				c.setColspan(1);
				c.setBorderColor(Color.CYAN);
				tableTag.addCell(c);
				
				c = new Cell("Descrizione tag");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(1);
				c.setHeader(true);
				c.setColspan(3);
				c.setBorderColor(Color.ORANGE);
				tableTag.addCell(c);
			
				tableTag.endHeaders();
				
				for (int x = 0; x < listaTag.size(); x++) {

					//conta righe
					c = new Cell(String.valueOf(x));
					c.setRowspan(2);
					c.setColspan(1);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tableTag.addCell(c);
					
					c = new Cell(String.valueOf(listaTag.get(x).getDescrizioneTag()));
					c.setRowspan(2);
					c.setColspan(3);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tableTag.addCell(c);

				}
				document.add(tableTag);
			}
			
			
			
			
			
			if(listaUtenti.size()>0) {
				
				document.newPage();
				
				Table tableUtente =new Table(20);
				tableUtente.setBorderWidth(2);
				tableUtente.setCellsFitPage(true);
				tableUtente.setBorderColor(Color.magenta);
				tableUtente.setPadding(3);
				tableUtente.setSpacing(0);
				tableUtente.setWidth(110);
				/*Cell c = new Cell("Liste di tutti Utenti");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setHeader(true);
				c.setRowspan(3);
				c.setColspan(11);
				tableUtente.addCell(c);*/
				
				
				
				// le prime  celle sopra
				par = new Paragraph("LISTA DEI UTENTI");
				par.getFont().setStyle(Font.BOLD);
				par.setAlignment(Element.ALIGN_CENTER);
				document.add(par);
				
				Cell c = new Cell("N righe");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(3);
				c.setHeader(true);
				c.setColspan(2);
				c.setBorderColor(Color.CYAN);
				tableUtente.addCell(c);
				
				c = new Cell("Codice fiscale");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(3);
				c.setHeader(true);
				c.setColspan(3);
				c.setBorderColor(Color.ORANGE);
				tableUtente.addCell(c);
				
				c = new Cell("Nome");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(3);
				c.setHeader(true);
				c.setColspan(2);
				c.setBorderColor(Color.ORANGE);
				tableUtente.addCell(c);
				
				c = new Cell("Cognome");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(3);
				c.setHeader(true);
				c.setColspan(3);
				c.setBorderColor(Color.ORANGE);
				tableUtente.addCell(c);
				
				c = new Cell("Email");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(3);
				c.setHeader(true);
				c.setColspan(5);
			
				c.setBorderColor(Color.ORANGE);
				tableUtente.addCell(c);
			
				c = new Cell("Soldi nel account");
				c.setHorizontalAlignment(HorizontalAlignment.CENTER);
				c.setRowspan(3);
				c.setHeader(true);
				c.setColspan(5);
				c.setBorderColor(Color.ORANGE);
				tableUtente.addCell(c);
				
				tableUtente.endHeaders();
				
				for (int x = 0; x < listaUtenti.size(); x++) {

					//conta righe
					c = new Cell(String.valueOf(x));
					c.setRowspan(3);
					c.setColspan(2);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tableUtente.addCell(c);
					
					c = new Cell(String.valueOf(listaUtenti.get(x).getCf()));
					c.setRowspan(3);
					c.setColspan(3);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tableUtente.addCell(c);
					
					c = new Cell(String.valueOf(listaUtenti.get(x).getNome()));
					c.setRowspan(3);
					c.setColspan(2);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tableUtente.addCell(c);
					
					c = new Cell(String.valueOf(listaUtenti.get(x).getCognome()));
					c.setRowspan(3);
					c.setColspan(3);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tableUtente.addCell(c);
					
					c = new Cell(String.valueOf(listaUtenti.get(x).getEmail()));
					c.setRowspan(3);
					c.setColspan(5);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tableUtente.addCell(c);
					
					c = new Cell(String.valueOf(listaUtenti.get(x).getSoldi()));
					c.setRowspan(3);
					c.setColspan(5);
					c.setBorderColor(Color.CYAN);
					c.setHorizontalAlignment(HorizontalAlignment.CENTER);
					tableUtente.addCell(c);

				}

				document.add(tableUtente);
			}
			
			
			
			// add text at an absolute position
			cb.beginText();
			cb.setFontAndSize(bf_times, 14);
			cb.setTextMatrix(100, 300);
			cb.showText("Text at position 100, 300.");
			cb.endText();

			// rotated text at an absolute position
			PdfTemplate template = cb.createTemplate(300, 300);
			template.beginText();
			template.setFontAndSize(bf_times, 14);
			template.showText("Rotated text at position 400, 200.");
			template.endText();

			float rotate = 90;
			float x = 400;
			float y = 200;
			float angle = (float) (-rotate * (Math.PI / 180));
			float xScale = (float) Math.cos(angle);
			float yScale = (float) Math.cos(angle);
			float xRot = (float) -Math.sin(angle);
			float yRot = (float) Math.sin(angle);

			cb.addTemplate(template, xScale, xRot, yRot, yScale, x, y);

			// we're done!
			document.close();

			return outPutStream.toByteArray();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return null;
	}

//---------------------------------------------------------------------

	public void inviaEmailProva(Asta asta) {

		String host = "smtp.gmail.com";
		final String user = "provajavaemailexolab@gmail.com";// change accordingly
		final String password = "megbtzwvgqsqlnyk";// change accordingly

		String to = String.valueOf(asta.getUtente().getEmail());// change accordingly

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("this is the result of the asta you won");
			message.setText("This is simple program of sending email using JavaMail API");

			// construct the text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(message.getSubject());

			// now write the PDF content to the output stream

			byte[] bytes = creaPdf(asta);

			// construct the pdf body part
			ByteArrayDataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("astavinta.pdf");

			// construct the mime multi part
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			message.setContent(mimeMultipart);
			Transport.send(message);

			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public byte[] creaPdf(Asta asta) {

		// creation of the document with a certain size and certain margins
		// (you can use PageSize.Letter instead of PageSize.A4)
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outPutStream);
		try {
			// creation of the different writers
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("OpenPDFExample.pdf"));

			// various fonts
			BaseFont bf_helv = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false);
			BaseFont bf_times = BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1252", false);
			BaseFont bf_courier = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false);
			BaseFont bf_symbol = BaseFont.createFont(BaseFont.SYMBOL, "Cp1252", false);

			// headers and footers must be added before the document is opened
			HeaderFooter footer = new HeaderFooter(new Phrase("This is page: ", new Font(bf_courier)), true);
			footer.setBorder(Rectangle.NO_BORDER);
			footer.setAlignment(Element.ALIGN_CENTER);
			document.setFooter(footer);

			HeaderFooter header = new HeaderFooter(
					new Phrase("This is a header without a page number", new Font(bf_symbol)), false);
			header.setAlignment(Element.ALIGN_CENTER);
			document.setHeader(header);

			document.open();

			int y_line1 = 650;
			int y_line2 = y_line1 - 50;
			int y_line3 = y_line2 - 50;

			// draw a few lines ...
			PdfContentByte cb = writer.getDirectContent();
			cb.setLineWidth(0f);
			cb.moveTo(250, y_line3 - 100);
			cb.lineTo(250, y_line1 + 100);
			cb.moveTo(50, y_line1);
			cb.lineTo(400, y_line1);
			cb.moveTo(50, y_line2);
			cb.lineTo(400, y_line2);
			cb.moveTo(50, y_line3);
			cb.lineTo(400, y_line3);
			cb.stroke();
			// ... and some text that is aligned in various ways
			cb.beginText();
			cb.setFontAndSize(bf_helv, 12);
			String text = "Sample text for alignment";
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text + " Center", 250, y_line1, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, text + " Right", 250, y_line2, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, text + " Left", 250, y_line3, 0);
			cb.endText();

			// add an image, scale it down by half, and put at an absolute position
			try {
				// Image simple = Image.getInstance("Simple.jpg");
				// simple.setAbsolutePosition(100, 100);
				// simple.scalePercent(50);
				// document.add(simple);
			} catch (Exception ex) {
				System.out.println("no image for you");
			}

			// start second page
			document.newPage();

			// add text in three paragraphs from top to bottom with various font styles
			Paragraph par = new Paragraph("bold paragraph");
			par.getFont().setStyle(Font.BOLD);
			document.add(par);
			par = new Paragraph("italic paragraph");
			par.getFont().setStyle(Font.ITALIC);
			document.add(par);
			par = new Paragraph("underlined and strike-through paragraph");
			par.getFont().setStyle(Font.UNDERLINE | Font.STRIKETHRU);
			document.add(par);

			// demonstrate some table features
			Table table = new Table(3);
			// 2 pixel wide blue border
			table.setBorderWidth(2);
			table.setBorderColor(new Color(0, 0, 255));
			table.setPadding(5);
			table.setSpacing(5);
			Cell c = new Cell("header");
			c.setHeader(true);
			c.setColspan(3);
			table.addCell(c);
			table.endHeaders();
			c = new Cell("example cell with rowspan 2 and red border");
			c.setRowspan(2);
			c.setBorderColor(new Color(255, 0, 0));
			table.addCell(c);
			table.addCell("1.1");
			table.addCell("2.1");
			table.addCell("1.2");
			table.addCell("2.2");
			c = new Cell("align center");
			c.setHorizontalAlignment(HorizontalAlignment.CENTER);
			table.addCell(c);
			Cell cell = new Cell("big cell");
			cell.setRowspan(2);
			cell.setColspan(2);

			table.addCell(cell);
			c = new Cell("align right");
			c.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			table.addCell(c);
			c = new Cell("dove sto");
			c.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			table.addCell(c);

			c = new Cell("dove sto parte 2");
			c.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			table.addCell(c);

			c = new Cell("dove sto parte 2");
			c.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			table.addCell(c);
			document.add(table);

			// add text at an absolute position
			cb.beginText();
			cb.setFontAndSize(bf_times, 14);
			cb.setTextMatrix(100, 300);
			cb.showText("Text at position 100, 300.");
			cb.endText();

			// rotated text at an absolute position
			PdfTemplate template = cb.createTemplate(300, 300);
			template.beginText();
			template.setFontAndSize(bf_times, 14);
			template.showText("Rotated text at position 400, 200.");
			template.endText();

			float rotate = 90;
			float x = 400;
			float y = 200;
			float angle = (float) (-rotate * (Math.PI / 180));
			float xScale = (float) Math.cos(angle);
			float yScale = (float) Math.cos(angle);
			float xRot = (float) -Math.sin(angle);
			float yRot = (float) Math.sin(angle);

			cb.addTemplate(template, xScale, xRot, yRot, yScale, x, y);

			// we're done!
			document.close();

			return outPutStream.toByteArray();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return null;
	}

}
