package br.com.franca.resource;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.franca.business.UsuarioBusiness;
import br.com.franca.dao.UsuarioDAO;
import br.com.franca.model.TokenUsuario;
import br.com.franca.model.Usuario;

@Path("/usuario")
public class LoginAPI {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//@Path("/usuario")
	
/*	public Response logar(String userName) {
		System.out.println(userName);
		 return Response.status(200).entity(new UsuarioDAO().buscarPor(userName)).build();
	}
*/	
	public Response efetuarLogin(TokenUsuario token) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		         
         
		TokenUsuario tokenOk = new UsuarioBusiness().validarUsuario(token);
		
		
		/*String originalInput = "test input";		
		
		System.out.println("1" + originalInput);
		
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		
		System.out.println("2" + encodedString);
		
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		
		System.out.println("3" + decodedBytes);
		
		String decodedString = new String(decodedBytes);
		
		System.out.println("4" + decodedString);
		*/
		
		
		 return Response.status(200).entity(new UsuarioDAO().buscarPor(token.getNome())).build();
	}
	
	/**
	 * Gera o token para ser enviado.
	 */
	protected String gerarToken(TokenUsuarioVO tokenUsuario) {
		String jwtString = null;
		try{
			jwtString = AuthHelper.createJsonWebToken(tokenUsuario, ContainerUtil.isMobileRequest(requestContext));
			jwtString = "Bearer " + jwtString;
		} catch(Exception e){
			logger.error(e, e);
		}
		return jwtString;
	}
	
	/*public Response salvar(Usuario usuario) {
		System.out.println(usuario);		
		return Response.status(200).entity(new UsuarioDAO().salvar(usuario)).build();
	}*/
}
