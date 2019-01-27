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
		String situacao = "Contratado";
		String sqlInsert = "INSERT INTO TB_FUNCIONARIO (nome, cpf, rg, orgao_exp, uf_rg, sexo,"
				+ " data_nascimento, email, celular, residencial, cep, endereco, bairro, cidade,"
				+ " estado, situacao, cargo, matricula, ativo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, funcionario.getNome());
			stm.setString(2, funcionario.getCpf());
			stm.setString(3, funcionario.getRg());
			stm.setString(4, funcionario.getOrgaoExp());
			stm.setString(5, funcionario.getUfRg());
			stm.setString(6, funcionario.getSexo());

			stm.setDate(7, new java.sql.Date(funcionario.getDataNascimento().getTimeInMillis()));
			stm.setString(8, funcionario.getEmail());
			stm.setString(9, funcionario.getCelular());
			stm.setString(10, funcionario.getResidencial());

			stm.setString(11, funcionario.getCep());
			stm.setString(12, funcionario.getEndereco());
			stm.setString(13, funcionario.getBairro());
			stm.setString(14, funcionario.getCidade());
			stm.setString(15, funcionario.getEstado());

			stm.setString(16, situacao);
			stm.setString(17, funcionario.getCargo());
			stm.setString(18, funcionario.getCpf());
			stm.setString(19, "Ativo");

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
		String situacao = "Contratado";
		String sqlUpdate = "UPDATE TB_FUNCIONARIO SET nome =?, cpf=?, rg=?, orgao_exp=?, uf_rg=?, sexo=?,"
				+ " data_nascimento=?, email=?, celular=?, residencial=?, cep=?, endereco=?, bairro=?, cidade=?, estado=?, "
				+ "situacao=?, cargo=?, matricula=?, ativo=? WHERE ID_FUNCIONARIO =?;";
		
		Connection connection = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stm = connection.prepareStatement(sqlUpdate);

			stm.setString(1, funcionario.getNome());
			stm.setString(2, funcionario.getCpf());
			stm.setString(3, funcionario.getRg());
			stm.setString(4, funcionario.getOrgaoExp());
			stm.setString(5, funcionario.getUfRg());
			stm.setString(6, funcionario.getSexo());

			stm.setDate(7, new java.sql.Date(funcionario.getDataNascimento().getTimeInMillis()));
			stm.setString(8, funcionario.getEmail());
			stm.setString(9, funcionario.getCelular());
			stm.setString(10, funcionario.getResidencial());

			stm.setString(11, funcionario.getCep());
			stm.setString(12, funcionario.getEndereco());
			stm.setString(13, funcionario.getBairro());
			stm.setString(14, funcionario.getCidade());
			stm.setString(15, funcionario.getEstado());

			stm.setString(16, funcionario.getCargo());
			stm.setString(17, funcionario.getCargo());
			stm.setString(18, funcionario.getCpf());
			stm.setString(19, funcionario.getAtivo());
			stm.setInt(20, funcionario.getId());

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
				funcionario.setOrgaoExp(rs.getString("orgao_exp"));
				funcionario.setUfRg(rs.getString("uf_rg"));

				java.sql.Date dataSql = rs.getDate("data_nascimento");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				funcionario.setDataNascimento(dataCalendar);

				funcionario.setSexo(rs.getString("sexo"));
				funcionario.setCelular(rs.getString("celular"));
				funcionario.setResidencial(rs.getString("residencial"));
				funcionario.setEmail(rs.getString("email"));

				funcionario.setCep(rs.getString("cep"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setBairro(rs.getString("bairro"));
				funcionario.setCidade(rs.getString("cidade"));
				funcionario.setEstado(rs.getString("estado"));
				funcionario.setAtivo(rs.getString("ativo"));

				funcionario.setMatricula(rs.getString("matricula"));
				funcionario.setSituacao(rs.getString("situacao"));
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
