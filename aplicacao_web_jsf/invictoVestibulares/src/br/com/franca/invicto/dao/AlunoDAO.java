package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.invicto.model.Aluno;
import br.com.franca.invicto.model.Turma;
import br.com.franca.invicto.model.Unidade;

public class AlunoDAO implements CrudDAO<Aluno> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public void salvar(Aluno aluno) {
		Connection connection = null;
		String sqlInsert = "INSERT INTO TB_ALUNO (nome, cpf, rg, orgao_exp,"
				+ " uf_rg, sexo, data_nascimento, email, celular," + " residencial, cep, endereco, bairro, cidade,"
				+ " estado, pai, mae, turma_id, ativo) VALUES (?,?,?,?," + "?,?,?,?,?," + "?,?,?,?,?," + "?,?,?,?,?);";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, aluno.getNome());
			stm.setString(2, aluno.getCpf());
			stm.setString(3, aluno.getRg());
			stm.setString(4, aluno.getOrgaoExp());

			stm.setString(5, aluno.getUfRg());			
			stm.setString(6, aluno.getSexo());
			stm.setDate(7, new java.sql.Date(aluno.getDataNascimento().getTimeInMillis()));
			stm.setString(8, aluno.getEmail());
			stm.setString(9, aluno.getCelular());
			stm.setString(10, aluno.getResidencial());

			stm.setString(11, aluno.getCep());
			stm.setString(12, aluno.getEndereco());
			stm.setString(13, aluno.getBairro());
			stm.setString(14, aluno.getCidade());
			stm.setString(15, aluno.getEstado());

			stm.setString(16, aluno.getPai());
			stm.setString(17, aluno.getMae());
			stm.setInt(18, aluno.getTurma().getId());
			stm.setString(19, "Ativo");

			linhas = stm.executeUpdate();

			aluno.setAtivo("Ativo");

			connection.commit();

			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				aluno.setId(rs.getInt(1));
			}

			System.out.println("Aluno salvo com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo salvar(Aluno aluno)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public void alterar(Aluno aluno) {
		Connection connection = null;
		String sqlUpdate = "UPDATE TB_ALUNO SET nome =?, cpf=?, rg=?, orgao_exp=?, uf_rg=?, sexo=?,"
				+ " data_nascimento=?, email=?, celular=?, residencial=?, cep=?, endereco=?, bairro=?, cidade=?,"
				+ " estado=?, pai=?, mae=? WHERE ID_ALUNO =?;";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlUpdate);

			stm.setString(1, aluno.getNome());
			stm.setString(2, aluno.getCpf());
			stm.setString(3, aluno.getRg());
			stm.setString(4, aluno.getOrgaoExp());

			stm.setString(5, aluno.getUfRg());
			stm.setString(6, aluno.getSexo());
			stm.setDate(7, new java.sql.Date(aluno.getDataNascimento().getTimeInMillis()));
			stm.setString(8, aluno.getEmail());
			stm.setString(9, aluno.getCelular());
			stm.setString(10, aluno.getResidencial());

			stm.setString(11, aluno.getCep());
			stm.setString(12, aluno.getEndereco());
			stm.setString(13, aluno.getBairro());
			stm.setString(14, aluno.getCidade());
			stm.setString(15, aluno.getEstado());

			stm.setString(16, aluno.getPai());
			stm.setString(17, aluno.getMae());

			stm.setInt(18, aluno.getId());

			linhas = stm.executeUpdate();

			connection.commit();
			
			System.out.println("Aluno alterado com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo alterar(Aluno aluno)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public void remover(Aluno aluno) {
		Connection connection = null;
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement("UPDATE TB_ALUNO SET ativo =? WHERE nome =?;");

			stm.setString(1, "Inativo");
			stm.setString(2, aluno.getNome());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Aluno removida com sucesso!");
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
	public List<Aluno> buscar() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno;
		Turma turma;
		Unidade unidade;
		String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {

				aluno = new Aluno();

				aluno.setId(rs.getInt("id_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setRg(rs.getString("rg"));
				aluno.setOrgaoExp(rs.getString("orgao_exp"));
				aluno.setUfRg(rs.getString("uf_rg"));

				java.sql.Date dataSql = rs.getDate("data_nascimento");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				aluno.setDataNascimento(dataCalendar);

				aluno.setCelular(rs.getString("celular"));
				aluno.setResidencial(rs.getString("residencial"));
				aluno.setEmail(rs.getString("email"));
				aluno.setPai(rs.getString("pai"));
				aluno.setMae(rs.getString("mae"));

				aluno.setCep(rs.getString("cep"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setAtivo(rs.getString("ativo"));

				aluno.getTurma().getUnidade().setNome(rs.getString(26));
				aluno.getTurma().getUnidade().setEndereco(rs.getString(27));
				aluno.getTurma().setNome(rs.getString(22));

				alunos.add(aluno);
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
		return alunos;
	}

	public Aluno buscarPorId(Integer id) {
		// List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = null;
		Turma turma;
		Unidade unidade;
		String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);			
			rs = stm.executeQuery();

			if (rs.next()) {

				aluno = new Aluno();

				aluno.setId(rs.getInt("id_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setRg(rs.getString("rg"));
				aluno.setOrgaoExp(rs.getString("orgao_exp"));
				aluno.setUfRg(rs.getString("uf_rg"));

				java.sql.Date dataSql = rs.getDate("data_nascimento");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				aluno.setDataNascimento(dataCalendar);

				aluno.setCelular(rs.getString("celular"));
				aluno.setResidencial(rs.getString("residencial"));
				aluno.setEmail(rs.getString("email"));
				aluno.setPai(rs.getString("pai"));
				aluno.setMae(rs.getString("mae"));

				aluno.setCep(rs.getString("cep"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setAtivo(rs.getString("ativo"));

				aluno.getTurma().getUnidade().setNome(rs.getString(26));
				aluno.getTurma().getUnidade().setEndereco(rs.getString(27));
				aluno.getTurma().setNome(rs.getString(22));
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
		return aluno;
	}

	public Aluno buscarPorCpf(String cpf) {
		// List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = null;
		Turma turma;
		Unidade unidade;
		String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE AND A.CPF=?;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);			
			stm.setString(1, cpf);
			rs = stm.executeQuery();

			if (rs.next()) {

				aluno = new Aluno();

				aluno.setId(rs.getInt("id_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setRg(rs.getString("rg"));
				aluno.setOrgaoExp(rs.getString("orgao_exp"));
				aluno.setUfRg(rs.getString("uf_rg"));

				java.sql.Date dataSql = rs.getDate("data_nascimento");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				aluno.setDataNascimento(dataCalendar);

				aluno.setCelular(rs.getString("celular"));
				aluno.setResidencial(rs.getString("residencial"));
				aluno.setEmail(rs.getString("email"));
				aluno.setPai(rs.getString("pai"));
				aluno.setMae(rs.getString("mae"));

				aluno.setCep(rs.getString("cep"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setAtivo(rs.getString("ativo"));

				aluno.getTurma().getUnidade().setNome(rs.getString(26));
				aluno.getTurma().getUnidade().setEndereco(rs.getString(27));
				aluno.getTurma().setNome(rs.getString(22));

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
		return aluno;
	}

}
