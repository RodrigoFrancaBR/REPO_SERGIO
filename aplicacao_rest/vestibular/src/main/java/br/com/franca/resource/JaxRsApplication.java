package br.com.franca.resource;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("servico")
public class JaxRsApplication extends ResourceConfig {

	@Inject
	public JaxRsApplication() {
		packages("br.com.franca.resource").register(JacksonFeature.class);
	}

}
