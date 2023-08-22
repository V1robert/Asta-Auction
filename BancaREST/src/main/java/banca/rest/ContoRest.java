package banca.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import banca.conf.EJBFactory;
import banca.interfaces.ContoInterface;

import banca.models.Conto;
import banca.models.Utente;

@Path("/contoRest")
public class ContoRest {

	private ContoInterface contoInterface;

	@POST
	@Path("/inserisciConto")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response inserisciConto(Conto conto) {
		try {
			contoInterface = new EJBFactory<ContoInterface>(ContoInterface.class).getEJB();
			Conto contoInserito = contoInterface.inserisciConto(conto);
			return Response.status(200).entity(contoInserito).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@POST
	@Path("/findContoByCf")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response findContoByCf(Utente utente) {
		try {
			contoInterface = new EJBFactory<ContoInterface>(ContoInterface.class).getEJB();
			List<Conto> listaConto = contoInterface.findContoByCf(utente.getCf());
			return Response.status(200).entity(listaConto).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@POST
	@Path("/findContiByStato")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response findContiByStato(Conto conto) {

		try {
			contoInterface = new EJBFactory<ContoInterface>(ContoInterface.class).getEJB();
			List<Conto> listaConti = contoInterface.findContiByStato(conto.getStatoConto().getId());
			return Response.status(200).entity(listaConti).build();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();

	}

	@PUT
	@Path("/attivaConto")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response attivaConto(Conto conto) {

		try {
			contoInterface = new EJBFactory<ContoInterface>(ContoInterface.class).getEJB();
			contoInterface.attivaConto(conto.getnConto());
			return Response.status(200).build();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();

	}
	@PUT
	@Path("/bloccaConto")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response bloccaConto(Conto conto) {
		
		try {
			contoInterface = new EJBFactory<ContoInterface>(ContoInterface.class).getEJB();
			contoInterface.bloccaConto(conto.getnConto());
			return Response.status(200).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
		
	}

}
