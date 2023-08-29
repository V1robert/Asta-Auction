package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.pdflib.PDFlibException;

import costanti.CostantiPdf;
import crud.ArticoloCrud;
import crud.AstaCrud;
import crud.TagCrud;
import crud.UtenteCrud;
import emailUtils.InviaEmail;
import interfaces.InviaEmailInterface;
import mapping.ArticoloMapping;
import mapping.AstaMapping;
import mapping.TagMapping;
import mapping.UserMapping;
import models.Articolo;
import models.Asta;
import models.Tag;
import models.Utente;
import pdfUtils.CreaPdfLib;
import pdfUtils.SendPDF;
import sqlfactory.SqlMapFactory;

@LocalBean
@Stateless()
public class InviaEmailController implements InviaEmailInterface {

	private InviaEmail inviaEmail;

	public void inviaListePdf(List<String> pdfScelti, Utente utenteLoggato, Boolean sceltaPdf) {

		inviaEmail = new InviaEmail();

		inviaEmail.inviaEmail(trovaListeScelte(pdfScelti), utenteLoggato, sceltaPdf);

	}

	public StreamedContent salvaInLocal(List<String> pdfScelti, Utente utenteLoggato, Boolean sceltaPdf) {
		SendPDF sendPdf = new SendPDF();
		try {
			InputStream is = new ByteArrayInputStream(
					sendPdf.creaPdfAdmin(trovaListeScelte(pdfScelti), utenteLoggato, sceltaPdf));
			StreamedContent doc = DefaultStreamedContent.builder().contentType("application/pdf")
					.name("C://Users//RobertVM//.eclipse//workspace2023//pdf/proviamo.pdf").stream(() -> is).build();
			return doc;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void creaPdfAstaConPdfLib() {
		try {
			CreaPdfLib crealo = new CreaPdfLib();
			List<Asta> tutteLeAste = trovaTutteLeAste();
			List<String> valoriLista = new ArrayList<String>();
AtomicInteger a;
			tutteLeAste.forEach((x) -> {
				valoriLista.add(String.valueOf(x.getDataScadenza()));
				valoriLista.add(String.valueOf(x.getPrezzoVenduto()));
				valoriLista.add(x.getUtente().getNome());
				valoriLista.add(String.valueOf(x.getIdOffertaPiuAlta()));
				valoriLista.add(x.getArticolo().getNomeArticolo());
				valoriLista.add(x.getTag().getDescrizioneTag());
			});

			crealo.creaPdfRiepilogoAsta(valoriLista);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public HashMap<Integer, Object> trovaListeScelte(List<String> pdfScelti) {
		List<Utente> listaUtenti = new ArrayList<Utente>();
		List<Asta> listaAste = new ArrayList<Asta>();
		List<Articolo> listaArticoli = new ArrayList<Articolo>();
		List<Tag> listaTag = new ArrayList<Tag>();

		HashMap<Integer, Object> listaDelleListe = new HashMap<Integer, Object>();

		for (int x = 0; x < pdfScelti.size(); x++) {

			if (pdfScelti.get(x).equalsIgnoreCase(CostantiPdf.ASTA)) {
				listaAste = trovaTutteLeAste();
				listaDelleListe.put(1, listaAste);
				System.out.println("trovati tutti i asta si");

			} else if (pdfScelti.get(x).equalsIgnoreCase(CostantiPdf.ARTICOLO)) {
				listaArticoli = trovaTuttiGliArticoli();
				listaDelleListe.put(2, listaArticoli);
				System.out.println("trovati tutti articoli si");

			} else if (pdfScelti.get(x).equalsIgnoreCase(CostantiPdf.UTENTE)) {
				listaUtenti = trovaTuttiUtenti();
				listaDelleListe.put(3, listaUtenti);
				System.out.println("trovati tutti i utenti si");

			} else if (pdfScelti.get(x).equalsIgnoreCase(CostantiPdf.TAG)) {
				listaTag = trovaTuttiTag();
				listaDelleListe.put(4, listaTag);
				System.out.println("trovati tutti i tag si");

			}
		}
		return listaDelleListe;
	}

	@Override
	public List<Asta> trovaTutteLeAste() {
		System.out.println("findAllLista aste Nel Controller inviaEmail--> ");
		try {
			SqlMapFactory.instance().openSession();
			AstaMapping mapper = SqlMapFactory.instance().getMapper(AstaMapping.class);
			AstaCrud astaCrud = new AstaCrud();
			List<Asta> aste = astaCrud.findAllAste(mapper);
			SqlMapFactory.instance().commitSession();
			return aste;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public List<Articolo> trovaTuttiGliArticoli() {
		System.out.println("trova Tuttiarticoli Controller articolo nel invia email controller --> ");
		try {
			SqlMapFactory.instance().openSession();
			ArticoloMapping mapper = SqlMapFactory.instance().getMapper(ArticoloMapping.class);
			ArticoloCrud articoloCrud = new ArticoloCrud();
			List<Articolo> articoli = articoloCrud.findAll(mapper);
			SqlMapFactory.instance().commitSession();
			return articoli;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public List<Tag> trovaTuttiTag() {
		System.out.println("findAllLista tag nel email Controller --> ");
		try {
			SqlMapFactory.instance().openSession();
			TagMapping mapper = SqlMapFactory.instance().getMapper(TagMapping.class);
			TagCrud tagCrud = new TagCrud();
			List<Tag> tage = tagCrud.findAll(mapper);
			SqlMapFactory.instance().commitSession();
			return tage;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public List<Utente> trovaTuttiUtenti() {
		System.out.println("findAll di utenti controller nel InviaEmail --> ");
		try {
			SqlMapFactory.instance().openSession();
			UserMapping mapper = SqlMapFactory.instance().getMapper(UserMapping.class);
			UtenteCrud utenteCrud = new UtenteCrud();
			List<Utente> utenti = utenteCrud.findAll(mapper);
			SqlMapFactory.instance().commitSession();
			return utenti;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public void inviaAstaVintaPdf(Asta asta, Utente utenteLoggato, Boolean sceltaPdf) {
		// TODO Auto-generated method stub
		inviaEmail = new InviaEmail();

		inviaEmail.inviaEmail(asta, utenteLoggato, sceltaPdf);
	}

}
