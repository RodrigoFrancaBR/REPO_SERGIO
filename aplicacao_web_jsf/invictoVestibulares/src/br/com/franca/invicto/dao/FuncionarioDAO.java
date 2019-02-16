package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.invicto.model.Funcionario;

public class FuncionarioDAO implements CrudDAO<Funcionario> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public void salvar(Funcionario funcionario) {
		Connection connection = null;
		
		String sqlInsert = "INSERT INTO TB_FUNCIONARIO (matricula, cargo, nome, cpf, rg, celular, email, ativo)"
				+ " VALUES (?,?,?,?,?,?,?,?);";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			// matricula do funcionario
			stm.setString(1, funcionario.getCpf());
			stm.setString(2, funcionario.getCargo());
			stm.setString(3, funcionario.getNome());		
			stm.setString(4, funcionario.getCpf());
			stm.setString(5, funcionario.getRg());
			stm.setString(6, funcionario.getCelular());				
			stm.setString(7, funcionario.getEmail());
			stm.setString(8, "Ativo");					

			linhas = stm.executeUpdate();

			funcionario.setAtivo("Ativo");
			connection.commit();
			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				funcionario.setId(rs.getInt(1));
			}

			System.out.println("Funcionario salvo com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo salvar(Funcionario funcionario)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public void alterar(Funcionario funcionario) {
		
		String sqlUpdate = "UPDATE TB_FUNCIONARIO SET nome =?, cpf=?, rg=?, email=?,"
				+ " celular=?, cargo=?, matricula=?, ativo=? WHERE ID_FUNCIONARIO =?;";
		
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stm = connection.prepareStatement(sqlUpdate);

			stm.setString(1, funcionario.getNome());
			stm.setString(2, funcionario.getCpf());
			stm.setString(3, funcionario.getRg());
			stm.setString(4, funcionario.getEmail());
			stm.setString(5, funcionario.getCelular());	
			stm.setString(6, funcionario.getCargo());			
			stm.setString(7, funcionario.getCpf());
			stm.setString(8, funcionario.getAtivo());
			stm.setInt(9, funcionario.getId());

			linhas = stm.executeUpdate();

			connection.commit();

			System.out.println("Funcionario alterado com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo alterar(Funcionario funcionario)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public void remover(Funcionario funcionario) {
		Connection connection = null;
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement("UPDATE TB_FUNCIONARIO SET ativo =? WHERE nome =?;");

			stm.setString(1, "Inativo");
			stm.setString(2, funcionario.getNome());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Pessoa removida com sucesso!");
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
	public List<Funcionario> buscar() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario funcionario;
		String sql = "SELECT * FROM TB_FUNCIONARIO;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {

				funcionario = new Funcionario();

				funcionario.setId(rs.getInt("id_funcionario"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setRg(rs.getString("rg"));							
				funcionario.setCelular(rs.getString("celular"));				
				funcionario.setEmail(rs.getString("email"));
				funcionario.setAtivo(rs.getString("ativo"));
				funcionario.setMatricula(rs.getString("matricula"));				
				funcionario.setCargo(rs.getString("cargo"));

				funcionarios.add(funcionario);
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
		return funcionarios;
	}
}
