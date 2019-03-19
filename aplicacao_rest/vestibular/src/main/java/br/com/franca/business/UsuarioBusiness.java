package br.com.franca.business;

import br.com.franca.dao.UsuarioDAO;
import br.com.franca.model.TokenUsuario;
import br.com.franca.model.Usuario;
import br.com.franca.util.CriptoUtil;

public class UsuarioBusiness {
	// vai passar o que o usuario escreveu e comparar com a senha que está no
	// banco, vai criptografar a senha digitada para verificar se é igual a que está criptografada no banco

	
	/**
	 * Valida a senha do usuário
	 * @param token Token enviado pelo client
	 * @param colaborador colaborador encontrado no banco através do ID
	 * @return true se a senha corresponde à senha informada, false se não corresponde
	 */
	private boolean validarSenha(TokenUsuario token, String senha) {
		System.out.println("Escreveu " + token.getSenha());
		System.out.println("Senha banco " + senha);
		boolean isValido = false;
		try {
			String senhaMD5 = CriptoUtil.criptoMD5(senha);
			System.out.println("Senha criptografada " + senhaMD5);	
			if (senhaMD5 != null && senhaMD5.equals(token.getSenha())) {
				isValido = true;
			}

		} catch (Exception e) {
			// logger.error(e, e);
		}
		return isValido;

	}

	public TokenUsuario validarUsuario(TokenUsuario token) {
		Usuario usuario = new UsuarioDAO().buscarUsuarioNome(token.getNome());
		if (usuario != null && usuario.getSenha() != null && this.validarSenha(token, usuario.getSenha())) {
			token.setId(usuario.getId());
			token.setNome(usuario.getNome());			
			token.setTipo(usuario.getTipo());
			token.setSenha(null);
		} else {
			throw new RuntimeException("Usuário ou Senha inválidos!");
		}
		return token;
	}

}
