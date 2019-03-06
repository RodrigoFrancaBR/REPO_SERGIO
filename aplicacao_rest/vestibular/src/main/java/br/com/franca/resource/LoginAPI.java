package br.com.franca.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.franca.dao.UsuarioDAO;
import br.com.franca.model.Usuario;

@Path("/usuario")
public class LoginAPI {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//@Path("/usuario")
	
	public Response logar(Usuario usuario) {
		System.out.println(usuario);
		// Usuario user = new UsuarioDAO().buscarPor(usuario.getNome());
		// if ()
		 return Response.status(200).entity(new UsuarioDAO().buscarPor(usuario.getNome())).build();
	}
	
	/*public Response salvar(Usuario usuario) {
		System.out.println(usuario);		
		return Response.status(200).entity(new UsuarioDAO().salvar(usuario)).build();
	}*/
}
