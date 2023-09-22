package it.exobank.conf;

import javax.naming.InitialContext;

public class EJBFactory<T> {
	
	private final static String PREFIX = "java:global/UploadEAR/UploadEJB/"; // percorso e nome del progetto con le EJB

	private Class<T> interfaceClass;
	
	// Costruttore parametrico
	public EJBFactory(Class<T> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}
	
	
	@SuppressWarnings("unchecked")
	public T getEJB() throws Exception {
		InitialContext context = new InitialContext(); //punto iniziale per la risoluzione del naming
		return (T) context.lookup(PREFIX + interfaceClass.getSimpleName() + "!" + interfaceClass.getName());
	}
}