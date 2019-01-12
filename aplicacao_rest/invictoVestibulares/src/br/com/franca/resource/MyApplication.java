package br.com.franca.resource;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/*

//Pode estender a class Application e identificar a classe de reosurce 
@ApplicationPath("webresources")
public class MyApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UnidadeResource.class);
		return s;
	}
}*/
	
	//Pode estender a class ResourceConfig que permite simplificar a forma de registrar um resource, nesse caso uso de scan para procurar
	// as classes de reosurces nos pacotes especificados
	@ApplicationPath("invicto")
	public class MyApplication extends ResourceConfig {
	    
		public MyApplication() {
	        packages("br.com.franca.resource").register(JacksonFeature.class);
	    }
}
