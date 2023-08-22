package banca.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import banca.conf.EJBFactory;
import banca.interfaces.TransazioneInterface;
import banca.models.Conto;
import banca.models.StatoTransazione;
import banca.models.Transazione;

@Path("/transazioneRest")
public class TransazioneRest {

	private TransazioneInterface transazioneInterface;

	@POST
	@Path("/findByStatoTransazione")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response findByStatoTransazione(StatoTransazione statoTransazione) {
		try {
			transazioneInterface = new EJBFactory<TransazioneInterface>(TransazioneInterface.class).getEJB();
			List<Transazione> listaTransazioni = transazioneInterface.findByStatoTransazione(statoTransazione.getId());
			return Response.status(200).entity(listaTransazioni).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@POST
	@Path("/findByContoUtente")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response findByConto(Conto contoUtente) {
		try {
			transazioneInterface = new EJBFactory<TransazioneInterface>(TransazioneInterface.class).getEJB();
			List<Transazione> listaTransazioni = transazioneInterface.findByConto(contoUtente.getnConto());
			return Response.status(200).entity(listaTransazioni).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@POST
	@Path("/insertTransazione")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response insertTransazione(Transazione transazione) {
		try {
			transazioneInterface = new EJBFactory<TransazioneInterface>(TransazioneInterface.class).getEJB();
			Transazione transazioneInserita = transazioneInterface.insertTransazione(transazione);
			return Response.status(200).entity(transazioneInserita).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@PUT
	@Path("/rifiutaTransazione")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response rifiutaTransazione(Transazione transazione) {
		try {
			transazioneInterface = new EJBFactory<TransazioneInterface>(TransazioneInterface.class).getEJB();
			transazioneInterface.rifiutaTransazione(transazione);
			return Response.status(200).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	@PUT
	@Path("/accettaTransazione")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response accettaTransazione(Transazione transazione) {
		try {
			transazioneInterface = new EJBFactory<TransazioneInterface>(TransazioneInterface.class).getEJB();
			transazioneInterface.accettaTransazione(transazione);
			return Response.status(200).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
}
