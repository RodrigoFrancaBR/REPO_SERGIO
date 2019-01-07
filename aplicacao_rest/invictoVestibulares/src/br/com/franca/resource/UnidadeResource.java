package br.com.franca.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.franca.dao.UnidadeDAO;
import br.com.franca.model.Unidade;

@Path("/unidade")
public class UnidadeResource {	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/unidades")
	public List<Unidade> buscar() {
		return new UnidadeDAO().buscar();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/unidade")
	public void salvar(Unidade unidade) {
		new UnidadeDAO().salvar(unidade);
	}
	
/*	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/unidade/{id}")
	public void alterar(Unidade unidade, @PathParam("id")long id){
		System.out.println(unidade.getId());
		System.out.println(unidade.getNome());
		System.out.println(id);
		new UnidadeDAO().alterar(unidade);
	} */
	
	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)	
	@Path("/unidade")
	public void alterar(Unidade unidade){
		System.out.println(unidade.getId());
		System.out.println(unidade.getNome());		
		new UnidadeDAO().alterar(unidade);
	} 
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/unidade")
	public void remove(Unidade unidade){
		new UnidadeDAO().remover(unidade);
	}
}
