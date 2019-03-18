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
	
	public Usuario buscarUsuarioNome(String nome) {
		Connection connection = new ConnectionFactory().getConnection();
		Usuario usuario = null;
		String sql = "SELECT * FROM TB_USUARIO WHERE NOME=? ";
		
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, nome);
			rs = stm.executeQuery();

			if (rs.next()) {

				usuario = new Usuario();

				usuario.setId(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getString("tipo"));
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscar(String nome)");
			e.printStackTrace();
			// throw new RuntimeException(e);
			try {
				System.out.println("Tentando realizar o roolback");
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Ocorreu algum erro ao tentar realizar o roolback");
				e1.printStackTrace();
				throw new RuntimeException(e1);
			}
			// throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeAll(connection, stm, rs);
			System.out.println(usuario);
		}		
		return usuario;
	}
	
	
	public Usuario buscarPor(String nome) {
		Connection connection = new ConnectionFactory().getConnection();
		Usuario usuario = null;
		String sql = "SELECT * FROM TB_USUARIO WHERE NOME=? ";
		
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, nome);
			rs = stm.executeQuery();

			if (rs.next()) {

				usuario = new Usuario();

				usuario.setId(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getString("tipo"));
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscar(String nome)");
			e.printStackTrace();
			// throw new RuntimeException(e);
			try {
				System.out.println("Tentando realizar o roolback");
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Ocorreu algum erro ao tentar realizar o roolback");
				e1.printStackTrace();
				throw new RuntimeException(e1);
			}
			// throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeAll(connection, stm, rs);
			System.out.println(usuario);
		}		
		return usuario;
	}
	
	
}
