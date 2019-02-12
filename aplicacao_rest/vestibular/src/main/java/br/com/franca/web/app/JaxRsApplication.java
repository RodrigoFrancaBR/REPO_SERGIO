package br.com.franca.web.app;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

//@ApplicationPath("servico")
public class JaxRsApplication extends ResourceConfig {
	
	//@Inject
	public JaxRsApplication() {
		packages("br.com.franca.web").register(JacksonFeature.class);
	}

}
