package br.com.franca.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class CORSFilter2 implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
		response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
		response.getHeaders().putSingle("Access-Control-Expose-Headers", "Authorization");
		response.getHeaders().putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, PATCH, DELETE");
		response.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Authorization, user-id");
	}

}