package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		String sqlInsert = "INSERT INTO TB_TURMA (nome, unidade_id, turno, ativo) values (?,?,?,?);";
		String sqlUpdate = "UPDATE TB_TURMA SET nome =?, unidade_id =?, turno=? WHERE id_turma =?;";
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			if (turma.getId() == null) {
				stm = connection.prepareStatement(sqlInsert);
				stm.setBoolean(4, true);
			} else {
				stm = connection.prepareStatement(sqlUpdate);
				stm.setInt(4, turma.getId());
			}

			stm.setString(1, turma.getNome());
			stm.setInt(2, turma.getUnidade().getId());
			stm.setString(3, turma.getTurno());

			linhas = stm.executeUpdate();

			connection.commit();
			System.out.println("Turma salva com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo cadastrarTurma(Turma turma)");
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
			stm = connection.prepareStatement("UPDATE TB_TURMA SET ativo =? WHERE nome =?;");

			stm.setBoolean(1, false);
			stm.setString(2, turma.getNome());
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
		String sql = "SELECT t.id_turma, t.nome, t.turno, t.ativo, u.id_unidade, u.nome, u.endereco, u.ativo FROM TB_TURMA as t, TB_UNIDADE as u WHERE t.unidade_id = u.id_unidade and (t.ATIVO AND u.ATIVO =?);";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			rs = stm.executeQuery();

			while (rs.next()) {

				turma = new Turma();
				unidade = new Unidade();

				turma.setId(rs.getInt("t.id_turma"));
				turma.setNome(rs.getString("t.nome"));
				turma.setTurno(rs.getString("t.turno"));
				turma.setAtivo(rs.getBoolean("t.ativo"));

				unidade.setId(rs.getInt("u.id_unidade"));
				unidade.setNome(rs.getString("u.nome"));
				unidade.setEndereco(rs.getString("u.endereco"));
				unidade.setAtivo(rs.getBoolean("u.ativo"));

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

}
