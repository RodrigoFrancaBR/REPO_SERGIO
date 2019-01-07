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
		String sqlInsert = "INSERT INTO TB_ALUNO (nome, sobrenome, cpf, rg, orgao_exp,"
				+ " uf_rg, sexo, data_nascimento, email, celular,"
				+ " residencial, cep, endereco, bairro, cidade,"
				+ " estado, pai, mae, turma_id, ativo) VALUE (?,?,?,?,?,"
				+ "?,?,?,?,?,"
				+ "?,?,?,?,?,"
				+ "?,?,?,?,?);";

		String sqlUpdate = "UPDATE TB_ALUNO SET nome =?, sobrenome=?, cpf=?, rg=?, orgao_exp=?, uf_rg=?, sexo=?,"
				+ " data_nascimento=?, email=?, celular=?, residencial=?, cep=?, endereco=?, bairro=?, cidade=?,"
				+ " estado=?, pai=?, mae=? WHERE ID_ALUNO =?;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			if (aluno.getId() == null) {
				stm = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);				
				stm.setInt(19, aluno.getTurma().getId());											
				stm.setBoolean(20, true);				

			} else {
				stm = connection.prepareStatement(sqlUpdate);
				stm.setInt(19, aluno.getId());
			}

			stm.setString(1, aluno.getNome());
			stm.setString(2, aluno.getSobreNome());
			stm.setString(3, aluno.getCpf());
			stm.setString(4, aluno.getRg());
			stm.setString(5, aluno.getOrgaoExp());
			
			stm.setString(6, aluno.getUfRg());
			stm.setString(7, aluno.getSexo());
			stm.setDate(8, new java.sql.Date(aluno.getDataNascimento().getTimeInMillis()));
			stm.setString(9, aluno.getEmail());
			stm.setString(10, aluno.getCelular());
			stm.setString(11, aluno.getResidencial());

			stm.setString(12, aluno.getCep());
			stm.setString(13, aluno.getEndereco());
			stm.setString(14, aluno.getBairro());
			stm.setString(15, aluno.getCidade());
			stm.setString(16, aluno.getEstado());
			
			stm.setString(17, aluno.getPai());	
			stm.setString(18, aluno.getMae());						

			linhas = stm.executeUpdate();

			aluno.setAtivo(true);
			
			connection.commit();
			
			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				aluno.setId(rs.getInt(1));
			}

			System.out.println("Aluno cadastrada com sucesso!");

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo cadastrarAluno(Aluno aluno)");
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

			stm.setBoolean(1, false);
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
		//String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U, TB_CONTRATO AS C WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE AND A.CONTRATO_ID = C.ID_CONTRATO AND A.ATIVO=?;";
		String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE AND A.ATIVO=?;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			rs = stm.executeQuery();

			while (rs.next()) {

				aluno = new Aluno();

				aluno.setId(rs.getInt("id_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSobreNome(rs.getString("sobrenome"));
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
				aluno.setAtivo(rs.getBoolean("ativo"));
				
				aluno.getTurma().getUnidade().setNome(rs.getString("U.NOME"));
				aluno.getTurma().getUnidade().setEndereco(rs.getString("U.ENDERECO"));
				aluno.getTurma().setNome(rs.getString("T.NOME"));								

				//aluno.getContrato().setMatricula(rs.getString("C.MATRICULA"));
								

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
		//List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = null;
		Turma turma;
		Unidade unidade;
		//String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U, TB_CONTRATO AS C WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE AND A.CONTRATO_ID = C.ID_CONTRATO AND A.ATIVO=?;";
		String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE AND A.ATIVO=?;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			rs = stm.executeQuery();

			if (rs.next()) {

				aluno = new Aluno();

				aluno.setId(rs.getInt("id_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSobreNome(rs.getString("sobrenome"));
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
				aluno.setAtivo(rs.getBoolean("ativo"));				
				
				aluno.getTurma().getUnidade().setNome(rs.getString("U.NOME"));
				aluno.getTurma().getUnidade().setEndereco(rs.getString("U.ENDERECO"));
				aluno.getTurma().setNome(rs.getString("T.NOME"));								

				//aluno.getContrato().setMatricula(rs.getString("C.MATRICULA"));
								

				//alunos.add(aluno);
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
		//List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = null;
		Turma turma;
		Unidade unidade;
		//String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U, TB_CONTRATO AS C WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE AND A.CONTRATO_ID = C.ID_CONTRATO AND A.ATIVO=?;";
		String sql = "SELECT * FROM TB_ALUNO AS A, TB_TURMA AS T, TB_UNIDADE AS U WHERE A.TURMA_ID = T.ID_TURMA AND T.UNIDADE_ID = U.ID_UNIDADE AND A.ATIVO=? AND A.CPF=?;";
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			stm.setString(2,cpf);
			rs = stm.executeQuery();

			if (rs.next()) {

				aluno = new Aluno();

				aluno.setId(rs.getInt("id_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSobreNome(rs.getString("sobrenome"));
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
				aluno.setAtivo(rs.getBoolean("ativo"));				
				
				aluno.getTurma().getUnidade().setNome(rs.getString("U.NOME"));
				aluno.getTurma().getUnidade().setEndereco(rs.getString("U.ENDERECO"));
				aluno.getTurma().setNome(rs.getString("T.NOME"));								

				//aluno.getContrato().setMatricula(rs.getString("C.MATRICULA"));
								

				//alunos.add(aluno);
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
