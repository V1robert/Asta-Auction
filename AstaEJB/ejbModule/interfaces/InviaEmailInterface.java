package interfaces;

import java.util.List;

import javax.ejb.Local;

import org.primefaces.model.StreamedContent;

import models.Articolo;
import models.Asta;
import models.Tag;
import models.Utente;

@Local
public interface InviaEmailInterface {

	public List<Asta> trovaTutteLeAste();

	public List<Articolo> trovaTuttiGliArticoli();

	public List<Tag> trovaTuttiTag();

	public List<Utente> trovaTuttiUtenti();

	public void inviaListePdf(List<String> pdfScelti, Utente utenteLoggato,Boolean sceltaPdf);
	
	public void inviaAstaVintaPdf(Asta asta, Utente utenteLoggato,Boolean sceltaPdf);
	
	public StreamedContent salvaInLocal(List<String> pdfScelti, Utente utenteLoggato,Boolean sceltaPdf) ;
	
	public void creaPdfAstaConPdfLib();
}
