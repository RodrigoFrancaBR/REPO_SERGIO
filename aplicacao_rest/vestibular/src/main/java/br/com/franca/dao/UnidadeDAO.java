package br.com.franca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		String sql = "SELECT * FROM TB_UNIDADE;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {

				unidade = new Unidade();

				unidade.setId(rs.getLong("id_unidade"));
				unidade.setNome(rs.getString("nome"));
				unidade.setEndereco(rs.getString("endereco"));
				unidade.setAtivo(rs.getString("ativo"));
				unidades.add(unidade);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscarTodos(Connection connection)");
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
			//
		}
		return unidades;
	}

	public Unidade salvar(Unidade unidade) {
		Connection connection = null;

		String sql = "INSERT INTO TB_UNIDADE (nome, endereco, ativo)" + " values (?,?,?)";
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());
			stm.setString(3, "Ativo");

			linhas = stm.executeUpdate();

			unidade.setAtivo("Ativo");

			connection.commit();

			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				unidade.setId(rs.getLong(1));
			}

			System.out.println("Unidade salva com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo salvar(Unidade unidade)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
		return unidade;

	}

	public void alterar(Unidade unidade) {
		Connection connection = null;
		String sqlUpdate = "UPDATE TB_UNIDADE SET nome =?, endereco = ?, ativo=? WHERE ID_UNIDADE =?;";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlUpdate);
			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());
			stm.setString(3, unidade.getAtivo());
			stm.setLong(4, unidade.getId());

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
			System.out.println("Ocorreu algum erro no metodo alterarUnidade(Unidade unidade)");
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
			stm = connection.prepareStatement("UPDATE TB_UNIDADE SET ativo =? WHERE id_unidade =?;");

			stm.setString(1, "Inativo");
			stm.setLong(2, unidade.getId());
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
			System.out.println("Ocorreu algum erro no metodo remover (String nome)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	public Unidade buscarPor(String nome) {
		Connection connection = new ConnectionFactory().getConnection();
		Unidade unidade = null;
		String sql = "SELECT * FROM TB_UNIDADE WHERE NOME=? ";
		System.out.println(nome);
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, nome);
			rs = stm.executeQuery();

			if (rs.next()) {

				unidade = new Unidade();

				unidade.setId(rs.getLong("id_unidade"));
				unidade.setNome(rs.getString("nome"));
				unidade.setEndereco(rs.getString("endereco"));
				unidade.setAtivo(rs.getString("ativo"));
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
			//
		}
		return unidade;
	}

}
