package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.franca.invicto.model.Categoria;

public class CategoriaDAO implements CrudDAO<Categoria> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public void salvar(Categoria categoria) {
		Connection connection = null;
		String sqlInsert = "INSERT INTO TB_CATEGORIA (nome, tipo_categoria, descricao, ativo)" + " values (?,?,?,?)";
		String sqlUpdate = "UPDATE TB_CATEGORIA SET nome =?, tipo_categoria =?, descricao=? WHERE id_categoria =?;";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			if (categoria.getId() == null) {
				stm = connection.prepareStatement(sqlInsert);
				stm.setBoolean(4, true);
			} else {
				stm = connection.prepareStatement(sqlUpdate);
				stm.setInt(4, categoria.getId());
			}
			stm.setString(1, categoria.getNome());
			stm.setString(2, categoria.getTipoCategoria());
			stm.setString(3, categoria.getDescricao());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Categoria salva com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo cadastrarCategoria(Categoria Categoria)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public void remover(Categoria categoria) {
		Connection connection = new ConnectionFactory().getConnection();
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement("UPDATE TB_CATEGORIA SET ativo =? WHERE id_categoria =?;");

			stm.setBoolean(1, false);
			stm.setInt(2, categoria.getId());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Categoria removida com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo remover(Categoria categoria)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public List<Categoria> buscar() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria categoria;
		String sql = "SELECT * FROM TB_CATEGORIA WHERE ATIVO =?";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			rs = stm.executeQuery();

			while (rs.next()) {

				categoria = new Categoria();

				categoria.setId(rs.getInt("id_Categoria"));
				categoria.setNome(rs.getString("nome"));
				categoria.setTipoCategoria(rs.getString("tipo_categoria"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setAtivo(rs.getBoolean("ativo"));
				categorias.add(categoria);
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
		return categorias;
	}

	/*public Categoria buscarPortipo(Categoria Categoria) {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "SELECT id_Categoria, nome, tipo, ativo FROM TB_CATEGORIA WHERE tipo =? AND ATIVO = ?;";
		Categoria CategoriaEncontrada = null;
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, Categoria.gettipo());
			stm.setBoolean(2, true);
			rs = stm.executeQuery();
			if (rs.next()) {
				System.out.println("Categoria encontrada!");
				CategoriaEncontrada = new Categoria();
				CategoriaEncontrada.setId(rs.getInt("id_Categoria"));
				CategoriaEncontrada.setNome(rs.getString("nome"));
				CategoriaEncontrada.settipo(rs.getString("tipo"));
				CategoriaEncontrada.setAtivo(rs.getBoolean("ativo"));
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
		return Categoria;
	}

	public Categoria buscarPorNome(Categoria Categoria) {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "SELECT id_Categoria, nome, tipo, ativo FROM TB_CATEGORIA WHERE nome =? AND ATIVO = ?;";
		Categoria CategoriaEncontrada = null;
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, Categoria.getNome());
			stm.setBoolean(2, true);
			rs = stm.executeQuery();
			if (rs.next()) {
				System.out.println("Categoria encontrada!");
				CategoriaEncontrada = new Categoria();
				CategoriaEncontrada.setId(rs.getInt("id_Categoria"));
				CategoriaEncontrada.setNome(rs.getString("nome"));
				CategoriaEncontrada.settipo(rs.getString("tipo"));
				CategoriaEncontrada.setAtivo(rs.getBoolean("ativo"));
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
		return Categoria;
	}

	public Categoria buscarPorId(Integer id) {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "SELECT id_Categoria, nome, tipo, ativo FROM TB_CATEGORIA WHERE id_Categoria =? AND ATIVO = ?;";
		Categoria CategoriaEncontrada = null;
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setInt(1, id);
			stm.setBoolean(2, true);
			rs = stm.executeQuery();
			if (rs.next()) {
				System.out.println("Categoria encontrada!");
				CategoriaEncontrada = new Categoria();
				CategoriaEncontrada.setId(rs.getInt("id_Categoria"));
				CategoriaEncontrada.setNome(rs.getString("nome"));
				CategoriaEncontrada.settipo(rs.getString("tipo"));
				CategoriaEncontrada.setAtivo(rs.getBoolean("ativo"));
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
		return CategoriaEncontrada;
	}
*/
	/*
	 * public void modificar(Connection connection, Categoria Categoria) {
	 * String sql =
	 * "UPDATE TB_CATEGORIA SET nome =?, tipo = ? WHERE ID_Categoria =?;"; try {
	 * connection.setAutoCommit(false); stm = connection.prepareStatement(sql);
	 * stm.setString(1, Categoria.getNome()); stm.setString(2,
	 * Categoria.gettipo()); stm.setInt(3, Categoria.getId());
	 * 
	 * linhas = stm.executeUpdate(); connection.commit();
	 * System.out.println("Dados modificados com sucesso!"); } catch (Exception
	 * e) { try { connection.rollback(); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } System.out.
	 * println("Ocorreu algum erro no metodo modificar(Categoria Categoria)");
	 * e.printStackTrace(); throw new RuntimeException(e); } finally {
	 * 
	 * ConnectionFactory.closeAll(connection, stm, rs); } }
	 */

	public void ativar(Categoria Categoria) {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "UPDATE TB_CATEGORIA SET ativo =? WHERE id_categoria =?;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			stm.setInt(2, Categoria.getId());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Categoria ativada com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo ativar(Categoria categoria)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeAll(connection, stm, rs);
		}
	}

}
