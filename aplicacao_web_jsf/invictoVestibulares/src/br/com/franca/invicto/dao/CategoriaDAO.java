package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.franca.invicto.model.Categoria;

public class CategoriaDAO implements CrudDAO<Categoria> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public Categoria salvar(Categoria categoria) {
		Connection connection = null;
		String sqlInsert = "INSERT INTO TB_CATEGORIA (nome, tipo_categoria, ativo)" + " values (?,?,?)";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			stm.setString(1, categoria.getNome());
			stm.setString(2, categoria.getTipoCategoria());
			stm.setString(3, "Ativo");

			linhas = stm.executeUpdate();

			categoria.setAtivo("Ativo");
			connection.commit();
			final ResultSet rs = stm.getGeneratedKeys();

			if (rs.next()) {
				categoria.setId(rs.getInt(1));
			}

			System.out.println("Categoria salva com sucesso!");
		} catch (

		Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo salvarCategoria(Categoria Categoria)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
		
		return categoria;

	}
	
	@Override
	public void alterar(Categoria categoria) {
		Connection connection = null;
		String sqlUpdate = "UPDATE TB_CATEGORIA SET nome =?, tipo_categoria =?, ativo=? WHERE id_categoria =?;";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlUpdate);

			stm.setString(1, categoria.getNome());
			stm.setString(2, categoria.getTipoCategoria());
			stm.setString(3, categoria.getAtivo());
			stm.setInt(4, categoria.getId());

			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Categoria alterada com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo alterar(Categoria Categoria)");
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

			stm.setString(1, "Inativo");
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
		String sql = "SELECT * FROM TB_CATEGORIA;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);			
			rs = stm.executeQuery();

			while (rs.next()) {

				categoria = new Categoria();

				categoria.setId(rs.getInt("id_Categoria"));
				categoria.setNome(rs.getString("nome"));
				categoria.setTipoCategoria(rs.getString("tipo_categoria"));
				categoria.setAtivo(rs.getString("ativo"));
				categorias.add(categoria);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscar(Connection connection)");
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
		
	public List<Categoria> buscarAtivos() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria categoria;
		String sql = "SELECT * FROM TB_CATEGORIA WHERE ATIVO='Ativo';";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);			
			rs = stm.executeQuery();

			while (rs.next()) {

				categoria = new Categoria();

				categoria.setId(rs.getInt("id_Categoria"));
				categoria.setNome(rs.getString("nome"));
				categoria.setTipoCategoria(rs.getString("tipo_categoria"));
				categoria.setAtivo(rs.getString("ativo"));
				categorias.add(categoria);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscar(Connection connection)");
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
	
	public Categoria buscarPor(Integer id_categoria) {
		Connection connection = new ConnectionFactory().getConnection();		
		Categoria categoria = null;
		String sql = "SELECT * FROM TB_CATEGORIA WHERE ID_CATEGORIA =?";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setInt(1, id_categoria);
			rs = stm.executeQuery();

			while (rs.next()) {

				categoria = new Categoria();

				categoria.setId(rs.getInt("id_Categoria"));
				categoria.setNome(rs.getString("nome"));
				categoria.setTipoCategoria(rs.getString("tipo_categoria"));
				categoria.setAtivo(rs.getString("ativo"));
				
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscarPor(Integer id_categoria)");
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
		return categoria;
	}


}
