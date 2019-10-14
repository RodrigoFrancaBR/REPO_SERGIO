package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.invicto.model.Situacao;
import br.com.franca.invicto.model.Turma;
import br.com.franca.invicto.model.Turma;
import br.com.franca.invicto.model.Unidade;

public class TurmaDAO implements CrudDAO<Turma> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public void salvar(Turma turma) {
		Connection connection = null;
		String sqlInsert = "INSERT INTO TB_TURMA (nome, unidade_id, situacao) values (?,?,?);";
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, turma.getNome());
			stm.setInt(2, turma.getUnidade().getId());
			stm.setInt(3, Situacao.ATIVA.getCodigo());

			linhas = stm.executeUpdate();
				
			connection.commit();
			final ResultSet rs = stm.getGeneratedKeys();
			
			if (rs.next()) {
				turma.setId(rs.getInt(1));
			}
			
			System.out.println("Turma salva com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo salvarTurma(Turma turma)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
	}

	@Override
	public void alterar(Turma turma) {
		Connection connection = null;
		String sqlUpdate = "UPDATE TB_TURMA SET nome =?, unidade_id =?, situacao=? WHERE id_turma =?;";
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlUpdate);

			stm.setString(1, turma.getNome());
			stm.setInt(2, turma.getUnidade().getId());
			stm.setInt(3, turma.getSituacao().getCodigo());
			stm.setInt(4, turma.getId());

			linhas = stm.executeUpdate();

			connection.commit();
			System.out.println("Turma alterada com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo alterarTurma(Turma turma)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
	}

	@Override
	public void remover(Turma turma) {
		Connection connection = new ConnectionFactory().getConnection();
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement("UPDATE TB_TURMA SET situacao =? WHERE id_turma =?;");

			stm.setInt(1, Situacao.DESATIVADA.getCodigo());
			stm.setInt(2, turma.getId());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Turma removida com sucesso!");
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
	public List<Turma> buscar() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Turma> turmas = new ArrayList<Turma>();
		Turma turma;
		Unidade unidade;
		// String sql = "SELECT * FROM TB_TURMA;";
		String sql = "SELECT t.id_turma, t.nome, t.situacao, u.id_unidade, u.nome, u.endereco FROM TB_TURMA as t, TB_UNIDADE as u WHERE t.unidade_id = u.id_unidade;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);			
			rs = stm.executeQuery();

			while (rs.next()) {

				turma = new Turma();
				unidade = new Unidade();

				turma.setId(rs.getInt(1));
				turma.setNome(rs.getString(2));
				turma.setSituacao(Situacao.valueOf(rs.getInt(3)));

				unidade.setId(rs.getInt(4));
				unidade.setNome(rs.getString(5));
				unidade.setEndereco(rs.getString(6));

				turma.setUnidade(unidade);

				turmas.add(turma);
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
		return turmas;
	}	
		
	public Turma buscarPorId(Integer id) {
		// List<Turma> Turmas = new ArrayList<Turma>();
		Turma turma = null;
		String sql = "SELECT * FROM TB_TURMA;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);			
			rs = stm.executeQuery();

			if (rs.next()) {

				turma = new Turma();
				turma.setId(rs.getInt("id_Turma"));
				turma.getUnidade().setId(rs.getInt("unidade_id"));;
				turma.setNome(rs.getString("nome"));
				turma.setSituacao(Situacao.valueOf(rs.getInt("situacao")));
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
		return turma;
	}
}
