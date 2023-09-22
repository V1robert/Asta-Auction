package it.exolab.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import costanti.CostantiArchivio;
import costanti.CostantiResponse;
import enums.StatoResponse;
import it.exobank.conf.EJBFactory;
import it.exolab.interfaces.FileDaCaricareInterface;
import model.EsitoOut;
import model.FileCaricato;

@Path("/fileDaCaricareRest")
public class FileDaCaricareRest {

	private FileDaCaricareInterface fileDaCaricareInterface;

	static final Logger log = Logger.getLogger(FileDaCaricareRest.class.getName());

	@POST
	@Path("/upload")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response verificaTipoDiArchivio(FileCaricato file) {

		EsitoOut esito = new EsitoOut();
		log.trace("sei nel metodo verificaTipoDiArchivio");

		try {
			EJBFactory<FileDaCaricareInterface> ejbFacroty = new EJBFactory<FileDaCaricareInterface>(
					FileDaCaricareInterface.class);
			fileDaCaricareInterface = ejbFacroty.getEJB();

			List<String> nomiDeiFile = fileDaCaricareInterface.verificaArchivio(file);

			esito.setStato(StatoResponse.OK);
			esito.setDescrizione(CostantiResponse.RESPONSE_ARCHIVIO_CORRETTO);
			esito.setCodice(CostantiResponse.Created_201);
			esito.setNomiDeiFile(nomiDeiFile);
			return Response.status(Response.Status.OK).entity(esito).build();
		} catch (Exception ex) {

			esito.setStato(StatoResponse.KO);

			ex.printStackTrace();
			log.error(ex.getMessage());
			if (ex.getMessage().equalsIgnoreCase(CostantiArchivio.EXCEPTION_ARCHIVIO)) {
				esito.setCodice(CostantiResponse.Unsupported_Media_Type415);
				esito.setDescrizione(CostantiArchivio.EXCEPTION_ARCHIVIO);
				return Response.status(Response.Status.OK).entity(esito).build();
			} else if (ex.getMessage().equalsIgnoreCase(CostantiArchivio.EXCEPTION_VERSIONE_RAR)) {
				esito.setCodice(CostantiResponse.Bad_Request_400);
				esito.setDescrizione(CostantiArchivio.EXCEPTION_VERSIONE_RAR);
				return Response.status(Response.Status.OK).entity(esito).build();
			} else if (ex.getMessage().equalsIgnoreCase(CostantiArchivio.MAGIC_NUMBER_NON_TROVATO)) {
				esito.setCodice(CostantiResponse.Bad_Request_400);
				esito.setDescrizione(CostantiArchivio.MAGIC_NUMBER_NON_TROVATO);
				return Response.status(Response.Status.OK).entity(esito).build();
			} else if (ex.getMessage().equalsIgnoreCase(CostantiArchivio.ERRORE_NEL_CONVERTIRE_INPUT_A_BYTEARRAY)) {
				esito.setCodice(CostantiResponse.Internal_Server_Error_500);
				esito.setDescrizione(CostantiResponse.RESPONSE_FILE_CONVERSIONE);
				return Response.status(Response.Status.OK).entity(esito).build();
			} else if (ex.getMessage().equalsIgnoreCase(CostantiArchivio.EXCEPTION_GRANDEZZA_FILE)) {
				esito.setCodice(CostantiResponse.Unprocessable_Content_422);
				esito.setDescrizione(CostantiResponse.RESPONSE_GRANDEZZA_ERROR);
				return Response.status(Response.Status.OK).entity(esito).build();
			}
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(CostantiResponse.RESPONSE_SERVER_DEFAULT_ERROR).build();
	}
}
