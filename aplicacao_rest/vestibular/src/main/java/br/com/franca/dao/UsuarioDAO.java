package br.com.franca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.franca.model.Usuario;

public class UsuarioDAO {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;
	
	public Usuario salvar(Usuario usuario) {
		Connection connection = null;

		String sql = "INSERT INTO TB_USUARIO (nome, senha)" + " values (?,?)";
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getSenha());			

			linhas = stm.executeUpdate();			

			connection.commit();

			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				usuario.setId(rs.getLong(1));
			}

			System.out.println("usuario salvo com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo salvar(usuario usuario)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
		return usuario;

	}
}
