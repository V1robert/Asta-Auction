package it.exobank.conf;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import it.exolab.rest.FileDaCaricareRest;


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
	
	public Set<Class<?>> getClasses(){
		Set<Class<?>> set = new HashSet<>();
		set.add(FileDaCaricareRest.class);
		return set;
	}
}
