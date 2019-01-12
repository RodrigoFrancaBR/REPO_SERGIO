package br.com.franca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.franca.model.Unidade;

public class UnidadeDAO {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	public List<Unidade> buscar() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Unidade> unidades = new ArrayList<Unidade>();
		Unidade unidade;
		String sql = "SELECT * FROM TB_UNIDADE WHERE ATIVO =?";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			rs = stm.executeQuery();

			while (rs.next()) {

				unidade = new Unidade();

				unidade.setId(rs.getLong("id_unidade"));
				unidade.setNome(rs.getString("nome"));
				unidade.setEndereco(rs.getString("endereco"));
				unidade.setAtivo(rs.getBoolean("ativo"));
				unidades.add(unidade);
			}
		} catch (SQLException e) {
			System.out
					.println("Ocorreu algum erro no metodo buscarTodos(Connection connection)");
			e.printStackTrace();
			// throw new RuntimeException(e);
			try {
				System.out.println("Tentando realizar o roolback");
				connection.rollback();
			} catch (SQLException e1) {
				System.out
						.println("Ocorreu algum erro ao tentar realizar o roolback");
				e1.printStackTrace();
				throw new RuntimeException(e1);
			}
			// throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeAll(connection, stm, rs);
			//
		}
		return unidades;
	}

	public void salvar(Unidade unidade) {
		Connection connection = null;

		String sql = "INSERT INTO TB_UNIDADE (nome, endereco, ativo)"
				+ " values (?,?,?)";
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());
			stm.setBoolean(3, true);
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Unidade salva com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out
					.println("Ocorreu algum erro no metodo cadastrarUnidade(Unidade unidade)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	public void alterar(Unidade unidade) {
		Connection connection = null;
		String sql = "UPDATE TB_UNIDADE SET nome =?, endereco =? WHERE ID_UNIDADE =?;";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());
			stm.setLong(3, unidade.getId());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Unidade alterada com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out
					.println("Ocorreu algum erro no metodo alterarUnidade(Unidade unidade)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	public void remover(Unidade unidade) {
		Connection connection = new ConnectionFactory().getConnection();
		try {
			connection.setAutoCommit(false);
			stm = connection
					.prepareStatement("UPDATE TB_UNIDADE SET ativo =? WHERE nome =?;");

			stm.setBoolean(1, false);
			stm.setString(2, unidade.getNome());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Unidade removida com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out
					.println("Ocorreu algum erro no metodo remover (String nome)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

}
