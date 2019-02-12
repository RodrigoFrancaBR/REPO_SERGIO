package br.com.franca.web.rs;

import javax.ws.rs.core.Response;

import br.com.franca.dao.UnidadeDAO;
import br.com.franca.web.api.UnidadeAPI;

public class UnidadeResource implements UnidadeAPI {

	//@Override
	public Response buscar() {
		return Response.status(200).entity(new UnidadeDAO().buscar()).build();
	}

}
