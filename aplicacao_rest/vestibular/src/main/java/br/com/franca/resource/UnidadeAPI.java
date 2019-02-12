package br.com.franca.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.franca.dao.UnidadeDAO;
import br.com.franca.model.Unidade;

@Path("/unidade")
public class UnidadeAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/unidades")
	public Response buscar() {
		return Response.status(200).entity(new UnidadeDAO().buscar()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscarPor(@QueryParam("nome") String nome) {
		return Response.status(200).entity(new UnidadeDAO().buscarPor(nome)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/unidade")
	public Response salvar(Unidade unidade) {
		return Response.status(200).entity(new UnidadeDAO().salvar(unidade)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/unidade")
	public void alterar(Unidade unidade) {
		System.out.println(unidade.getId());
		System.out.println(unidade.getNome());
		new UnidadeDAO().alterar(unidade);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/unidade")
	public void remove(Unidade unidade) {
		new UnidadeDAO().remover(unidade);
	}
}
