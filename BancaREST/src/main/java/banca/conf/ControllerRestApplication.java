package banca.conf;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import banca.models.Transazione;
import banca.rest.ContoRest;
import banca.rest.TransazioneRest;
import banca.rest.UtenteRest;




@ApplicationPath("/rest")
public class ControllerRestApplication extends Application {
	
private Set<Object> singletons; 

	
	public ControllerRestApplication() { 
		super(); 
	    CorsFilter corsFilter = new CorsFilter(); 
	    corsFilter.getAllowedOrigins().add("*"); 
	    corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH"); 
	    corsFilter.setAllowedHeaders("Authorization, Content-Type, Accept, Origin"); // Imposta gli header consentiti
	    corsFilter.setAllowCredentials(true); // Abilita l'invio delle credenziali (es. cookie) nelle richieste CORS
	    corsFilter.setCorsMaxAge(86400); // Imposta il tempo massimo di memorizzazione in cache delle risposte CORS in secondi (86400 secondi = 24 ore)
	    singletons = new HashSet<Object>(); 
	    singletons.add(corsFilter); 
		}
	
	public Set<Object> getSingletons() { 
	 return singletons; 
	} 
	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> set = new HashSet<>();
        set.add(UtenteRest.class);
        set.add(TransazioneRest.class);
        set.add(ContoRest.class);
		return set;
	}


}
