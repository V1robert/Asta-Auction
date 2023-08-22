package banca.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import banca.conf.EJBFactory;
import banca.interfaces.ContoInterface;
import banca.interfaces.UtenteInterface;
import banca.models.Conto;
import banca.models.Utente;
import dto.UtenteDto;

@Path("/utenteRest")
public class UtenteRest {

	private UtenteInterface utenteInterface;
	
	private ContoInterface contoInterface;

	@POST
	@Path("/provaAsta")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response provaAsta(UtenteDto utente) {
		try {
			//trovo il conto
			contoInterface = new EJBFactory<ContoInterface>(ContoInterface.class).getEJB();
			List<Conto> listaConto = contoInterface.findContoByCf(utente.getCodiceFiscale());
			
			Conto conto=new Conto();
			conto.setSaldo(listaConto.get(0).getSaldo());
			conto.setnConto(listaConto.get(0).getnConto());
			
			conto.setSaldo(conto.getSaldo()-utente.getImportoDaPagare());
			
			contoInterface.modificaSaldo(conto);
			
			utente.getListaUtenti().get(0).setEmail("ermojjarra");
			utente.getListaUtenti().get(0).setCodiceFiscale("scollate");
			utente.setImportoDaPagare((int) Math.round(conto.getSaldo()));
	
			
			return Response.status(200).entity(utente).build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	@POST
	@Path("/registrazioneUtente")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response registrazioneUtente(Utente utente) {
		try {
			System.out.println("sono nel rest pre");
			utenteInterface = new EJBFactory<UtenteInterface>(UtenteInterface.class).getEJB();
			Utente utenteInserito = utenteInterface.registrazioneUtente(utente);
			System.out.println("sono nel rest post");
			return Response.status(200).entity(utenteInserito).build();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@POST
	@Path("/loginUtente")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response findByEmailAndPwd(Utente utente) {
		try {
			utenteInterface = new EJBFactory<UtenteInterface>(UtenteInterface.class).getEJB();
			Utente utenteRestituito = utenteInterface.findByMailAndPwd(utente.getMail(), utente.getPwd());
			return Response.status(200).entity(utenteRestituito).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@POST
	@Path("/listaUtenti")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response findAllUtenti() {
		try {
			utenteInterface = new EJBFactory<UtenteInterface>(UtenteInterface.class).getEJB();
			List<Utente> listaUtenti = utenteInterface.findAllUtenti();
			return Response.status(200).entity(listaUtenti).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	@POST
	@Path("/listaStaff")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response findAllStaff() {
		try {
			utenteInterface = new EJBFactory<UtenteInterface>(UtenteInterface.class).getEJB();
			List<Utente> listaUtenti = utenteInterface.findAllStaff();
			return Response.status(200).entity(listaUtenti).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@POST
	@Path("/findByCf")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response findByCf(Utente utente) {
		try {
			utenteInterface = new EJBFactory<UtenteInterface>(UtenteInterface.class).getEJB();
			Utente utenteDaTrovare = utenteInterface.findByCf(utente.getCf());
			return Response.status(200).entity(utenteDaTrovare).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@POST
	@Path("/findById")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response findById(Utente utente) {
		try {
			utenteInterface = new EJBFactory<UtenteInterface>(UtenteInterface.class).getEJB();
			Utente utenteDaTrovare = utenteInterface.findById(utente.getId());
			return Response.status(200).entity(utenteDaTrovare).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@PUT
	@Path("/updateUtente")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response updateUtente(Utente utente) {
		try {
			utenteInterface = new EJBFactory<UtenteInterface>(UtenteInterface.class).getEJB();
			Utente utenteDaModificare = utenteInterface.updateUtente(utente);
			return Response.status(200).entity(utenteDaModificare).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

	@PUT
	@Path("/updateUtenteByAdmin")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response updateUtenteByAdmin(Utente utente) {
		try {
			utenteInterface = new EJBFactory<UtenteInterface>(UtenteInterface.class).getEJB();
			Utente utenteDaModificare = utenteInterface.updateUtenteByAdmin(utente);
			return Response.status(200).entity(utenteDaModificare).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	
}
