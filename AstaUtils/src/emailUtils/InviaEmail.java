package emailUtils;

import java.util.HashMap;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import models.Asta;
import models.Utente;
import pdfUtils.SendPDF;
import costanti.CostantiEmail;

public class InviaEmail {

	public void inviaEmail(HashMap<Integer, Object> listaDiListe, Utente utenteLoggato,Boolean sceltaPdf) {

		// sett email per l'admin con la sessione
		Session session = Session.getInstance(creaPropietaEmail(), new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(CostantiEmail.USER, CostantiEmail.PASSWORD);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(CostantiEmail.USER));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(CostantiEmail.EMAILDESTINATARIO)); // change// accordingly

			message.setSubject(CostantiEmail.RISULTATOASTA);

			// construct the text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(message.getSubject());

			// now write the PDF content to the output stream

			SendPDF inviaPdfEmail = new SendPDF();

			byte[] bytes = inviaPdfEmail.creaPdfAdmin(listaDiListe, utenteLoggato,sceltaPdf);

			// construct the pdf body part
			MimeBodyPart pdfBodyPart = creaPdfBodyPart(bytes);

			// construct the mime multi part
			MimeMultipart mimeMultipart = creaMimeMultiPart(pdfBodyPart, textBodyPart);

			message.setContent(mimeMultipart);
			Transport.send(message);

			System.out.println(CostantiEmail.MESSAGGIOSUCESSO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inviaEmail(Asta asta,Utente utenteLoggato,Boolean sceltaPdf) {

		// sett email per l'admin con la sessione
		Session session = Session.getInstance(creaPropietaEmail(), new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(CostantiEmail.USER, CostantiEmail.PASSWORD);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(CostantiEmail.USER));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(utenteLoggato.getEmail())); 
																													
			message.setSubject(CostantiEmail.RISULTATOASTA);

			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(message.getSubject());

			SendPDF inviaPdfEmail = new SendPDF();

			byte[] bytes = inviaPdfEmail.creaPdf(asta,utenteLoggato,sceltaPdf);

			MimeBodyPart pdfBodyPart = creaPdfBodyPart(bytes);

			MimeMultipart mimeMultipart = creaMimeMultiPart(pdfBodyPart, textBodyPart);

			message.setContent(mimeMultipart);
			Transport.send(message);

			System.out.println(CostantiEmail.MESSAGGIOSUCESSO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	private Properties creaPropietaEmail() {
		Properties properties = new Properties();
		properties.put(CostantiEmail.SMTPHOST, CostantiEmail.HOST);
		properties.put(CostantiEmail.SMTPPORT, CostantiEmail.SMTPPORTNUMBER);
		properties.put(CostantiEmail.SMTPSSL, CostantiEmail.SMTPVALUETRUE);
		properties.put(CostantiEmail.SMTPAUTH, CostantiEmail.SMTPVALUETRUE);

		return properties;

	}

	// metodo per creare il bodypart del pdf che verra aggiunto dopo

	private MimeBodyPart creaPdfBodyPart(byte[] bytes) throws MessagingException {
		// construct the pdf body part

		ByteArrayDataSource dataSource = new ByteArrayDataSource(bytes, CostantiEmail.FORMATOFILEPDF);
		MimeBodyPart pdfBodyPart = new MimeBodyPart();
		pdfBodyPart.setDataHandler(new DataHandler(dataSource));
		pdfBodyPart.setFileName(CostantiEmail.NOMEFILEASTA);
		return pdfBodyPart;

	}

	// metodo per unire alla mail il corpo del messaggio piu il file pdf sotto

	private MimeMultipart creaMimeMultiPart(MimeBodyPart pdfBodyPart, MimeBodyPart textBodyPart)
			throws MessagingException {

		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.addBodyPart(textBodyPart);
		mimeMultipart.addBodyPart(pdfBodyPart);

		return mimeMultipart;
	}

}
