package br.com.franca.business;

import br.com.franca.dao.UsuarioDAO;
import br.com.franca.model.TokenUsuario;
import br.com.franca.model.Usuario;
import br.com.franca.util.CriptoUtil;

public class UsuarioBusiness {
	// vai passar o que o usuario escreveu e comparar com a senha que está no
	// banco

	private boolean validarSenha(TokenUsuario token, String senha) {
		boolean isValido = false;
		try {
			String senhaMD5 = CriptoUtil.criptoMD5(senha);
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
			token.setNome(usuario.getNome());
			token.setId(usuario.getId());
		} else {
			throw new RuntimeException("Usuário ou Senha inválidos!");
		}
		return token;
	}

}
