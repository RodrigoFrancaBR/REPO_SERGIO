package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.franca.invicto.model.Unidade;

public class UnidadeDAO implements CrudDAO<Unidade> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public void salvar(Unidade unidade) {
		Connection connection = null;
		String sqlInsert = "INSERT INTO TB_UNIDADE (nome, endereco, ativo)" + " values (?,?,?)";
		String sqlUpdate = "UPDATE TB_UNIDADE SET nome =?, endereco = ? WHERE ID_UNIDADE =?;";
		
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			
			if (unidade.getId() == null) {
				stm = connection.prepareStatement(sqlInsert);
				stm.setBoolean(3, true);
			} else {
				stm = connection.prepareStatement(sqlUpdate);
				stm.setInt(3, unidade.getId());
			}
			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());
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
			System.out.println("Ocorreu algum erro no metodo cadastrarUnidade(Unidade unidade)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public void remover(Unidade unidade) {
		Connection connection = new ConnectionFactory().getConnection();
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement("UPDATE TB_UNIDADE SET ativo =? WHERE nome =?;");

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
			System.out.println("Ocorreu algum erro no metodo remover (String nome)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
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

				unidade.setId(rs.getInt("id_unidade"));
				unidade.setNome(rs.getString("nome"));
				unidade.setEndereco(rs.getString("endereco"));
				unidade.setAtivo(rs.getBoolean("ativo"));
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

	public Unidade buscarPorEndereco(Unidade unidade) {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "SELECT id_unidade, nome, endereco, ativo FROM TB_UNIDADE WHERE endereco =? AND ATIVO = ?;";
		Unidade unidadeEncontrada = null;
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, unidade.getEndereco());
			stm.setBoolean(2, true);
			rs = stm.executeQuery();
			if (rs.next()) {
				System.out.println("Unidade encontrada!");
				unidadeEncontrada = new Unidade();
				unidadeEncontrada.setId(rs.getInt("id_unidade"));
				unidadeEncontrada.setNome(rs.getString("nome"));
				unidadeEncontrada.setEndereco(rs.getString("endereco"));
				unidadeEncontrada.setAtivo(rs.getBoolean("ativo"));
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo buscar(String nome)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
		return unidade;
	}

	public Unidade buscarPorNome(Unidade unidade) {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "SELECT id_unidade, nome, endereco, ativo FROM TB_UNIDADE WHERE nome =? AND ATIVO = ?;";
		Unidade unidadeEncontrada = null;
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, unidade.getNome());
			stm.setBoolean(2, true);
			rs = stm.executeQuery();
			if (rs.next()) {
				System.out.println("Unidade encontrada!");
				unidadeEncontrada = new Unidade();
				unidadeEncontrada.setId(rs.getInt("id_unidade"));
				unidadeEncontrada.setNome(rs.getString("nome"));
				unidadeEncontrada.setEndereco(rs.getString("endereco"));
				unidadeEncontrada.setAtivo(rs.getBoolean("ativo"));
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo buscar(String nome)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
		return unidade;
	}
	
	public Unidade buscarPorId(Integer id) {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "SELECT id_unidade, nome, endereco, ativo FROM TB_UNIDADE WHERE id_unidade =? AND ATIVO = ?;";
		Unidade unidadeEncontrada = null;
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setInt(1, id);
			stm.setBoolean(2, true);
			rs = stm.executeQuery();
			if (rs.next()) {
				System.out.println("Unidade encontrada!");
				unidadeEncontrada = new Unidade();
				unidadeEncontrada.setId(rs.getInt("id_unidade"));
				unidadeEncontrada.setNome(rs.getString("nome"));
				unidadeEncontrada.setEndereco(rs.getString("endereco"));
				unidadeEncontrada.setAtivo(rs.getBoolean("ativo"));
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo buscar(String nome)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
		return unidadeEncontrada;
	}

	/*public void modificar(Connection connection, Unidade unidade) {
		String sql = "UPDATE TB_UNIDADE SET nome =?, endereco = ? WHERE ID_UNIDADE =?;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());
			stm.setInt(3, unidade.getId());

			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Dados modificados com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo modificar(Unidade unidade)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
	}*/

	public void ativar(Unidade unidade) {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "UPDATE TB_UNIDADE SET ativo =? WHERE nome =?;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			stm.setString(2, unidade.getNome());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Unidade ativada com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo ativar(String nome)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeAll(connection, stm, rs);
		}
	}

}
