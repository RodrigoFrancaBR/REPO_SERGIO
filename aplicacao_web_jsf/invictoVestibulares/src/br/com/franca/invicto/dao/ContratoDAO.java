package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Situacao;

public class ContratoDAO implements CrudDAO<Contrato> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public void salvar(Contrato contrato) {
		System.out.println(contrato.toString());
		contrato.setMatricula(contrato.getDataMatricula().get(Calendar.YEAR) + contrato.getAluno().getCpf().substring(0,4)+ contrato.getTurma().getNome());
		// CondicaoDoContrato cd = new CondicaoDoContrato();
		contrato.setSituacao(Situacao.MATRICULADO);
		System.out.println(contrato.getMatricula());
		// CondicaoDoContrato condicao = contrato.getCondicaoDoContrato();
		// contrato.setCondicaoDoContrato(contrato);

		Connection connection = null;
		String sqlInsert = "INSERT INTO TB_CONTRATO (taxa_matricula, valor_curso, desconto_curso, qtd_parcelas_curso, valor_material,"
				+ " qtd_parcelas_material, dia_vencimento, forma_pagamento, data_matricula, situacao, aluno_id, matricula, condicao_contrato)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			stm.setBigDecimal(1, contrato.getTaxaMatricula());
			stm.setBigDecimal(2, contrato.getValorCurso());
			stm.setDouble(3, contrato.getDescontoCurso());
			stm.setInt(4, contrato.getQtdParcelasCurso());
			stm.setBigDecimal(5, contrato.getValorMaterial());
			stm.setInt(6, contrato.getQtdParcelasMaterial());
			stm.setInt(7, contrato.getDiaVencimento());
			stm.setString(8, contrato.getFormaDePagamento());

			stm.setDate(9, new java.sql.Date(contrato.getDataMatricula().getTimeInMillis()));
			stm.setInt(10, contrato.getSituacao().getNumero()); 
			// stm.setSituacao(10, contrato.getSituacaoContrato().values());
			stm.setInt(11, contrato.getAluno().getId());
			stm.setString(12, contrato.getMatricula());
			stm.setString(13, contrato.getCondicaoDoContrato().toString());

			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("contrato salva com sucesso!");
			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				contrato.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo cadastrarcontrato(contrato contrato)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public void remover(Contrato entidade) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Contrato> buscar() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Contrato> contratos = new ArrayList<Contrato>();
		Contrato contrato;
		String sql = "SELECT * FROM TB_CONTRATO AS C, TB_ALUNO AS A WHERE C.ALUNO_ID = A.ID_ALUNO;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {

				contrato = new Contrato();

				contrato.setId(rs.getInt("id_contrato"));
				contrato.setTaxaMatricula(rs.getBigDecimal("taxa_matricula"));
				contrato.setValorCurso(rs.getBigDecimal("valor_curso"));
				contrato.setDescontoCurso(rs.getDouble("desconto_curso"));
				contrato.setQtdParcelasCurso(rs.getInt("qtd_parcelas_curso"));
				contrato.setValorMaterial(rs.getBigDecimal("valor_material"));
				contrato.setQtdParcelasMaterial(rs.getInt("qtd_parcelas_material"));
				contrato.setDiaVencimento(rs.getInt("dia_vencimento"));
				contrato.setFormaDePagamento(rs.getString("forma_pagamento"));

				java.sql.Date dataSql = rs.getDate("data_matricula");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				contrato.setDataMatricula(dataCalendar);
				// contrato.setSituacaoMatricula(rs.getString("situacao_matricula"));
				contrato.setCondicaoDoContrato(contrato.getQtdParcelasCurso(),contrato.getQtdParcelasMaterial());

				String matricula = rs.getString("matricula");
				String[] letras = { ".", "-" };
				for (String letra : letras) {
					matricula = matricula.replace(letra, "");
				}

				contrato.setMatricula(matricula.trim());

				contrato.getAluno().setId(rs.getInt("aluno_id"));
				contrato.getAluno().setNome(rs.getString("nome"));

				contratos.add(contrato);
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

		}
		return contratos;
	}

	public Contrato buscarPorMatricula(String matricula) {
		Connection connection = new ConnectionFactory().getConnection();
		Contrato contrato = null;

		/*
		 * String sql =
		 * "SELECT * FROM TB_CONTRATO AS C, TB_ALUNO AS A WHERE C.ALUNO_ID = A.ID_ALUNO AND C.MATRICULA =? "
		 * + "AND C.SITUACAO_MATRICULA =?;";
		 */
		String sql = "SELECT * FROM TB_CONTRATO AS C, TB_ALUNO AS A WHERE C.ALUNO_ID = A.ID_ALUNO AND "
				+ "C.MATRICULA =? AND C.SITUACAO=?;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, matricula);
			stm.setString(2, "Processo de Matrícula");
			rs = stm.executeQuery();

			if (rs.next()) {

				contrato = new Contrato();

				contrato.setId(rs.getInt("id_contrato"));

				contrato.setTaxaMatricula(rs.getBigDecimal("taxa_matricula"));

				contrato.setValorCurso(rs.getBigDecimal("valor_curso"));

				contrato.setDescontoCurso(rs.getDouble("desconto_curso"));

				contrato.setQtdParcelasCurso(rs.getInt("qtd_parcelas_curso"));

				contrato.setValorMaterial(rs.getBigDecimal("valor_material"));

				contrato.setQtdParcelasMaterial(rs.getInt("qtd_parcelas_material"));

				contrato.setDiaVencimento(rs.getInt("dia_vencimento"));

				contrato.setFormaDePagamento(rs.getString("forma_pagamento"));

				java.sql.Date dataSql = rs.getDate("data_matricula");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());

				contrato.setDataMatricula(dataCalendar);

				// contrato.setSituacaoMatricula(rs.getString("SITUACAO"));

				// contrato.setCondicaoDoContrato(contrato);
				// rs.getString("condicao_contrato")
				contrato.setCondicaoDoContrato(contrato.getQtdParcelasCurso(),contrato.getQtdParcelasMaterial());

				String matriculaEncontrada = rs.getString("matricula");

				String[] letras = { ".", "-" };

				for (String letra : letras) {
					matriculaEncontrada = matriculaEncontrada.replace(letra, "");
				}

				contrato.setMatricula(matriculaEncontrada);

				contrato.getAluno().setId(rs.getInt("aluno_id"));

				contrato.getAluno().setNome(rs.getString("nome"));

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
		return contrato;
	}

	@Override
	public void alterar(Contrato contrato) {
		Connection connection = null;
		String sqlUpdate = "UPDATE TB_CONTRATO SET taxa_matricula=?, valor_curso=?, desconto_curso=?, qtd_parcelas_curso=?,"
				+ " valor_material=?, qtd_parcelas_material=?, dia_vencimento=?, forma_pagamento=?, data_matricula=? "
				+ " WHERE id_contrato=?;";

		try {
			connection = new ConnectionFactory().getConnection();

			connection.setAutoCommit(false);

			stm = connection.prepareStatement(sqlUpdate);

			stm.setBigDecimal(1, contrato.getTaxaMatricula());
			stm.setBigDecimal(2, contrato.getValorCurso());
			stm.setDouble(3, contrato.getDescontoCurso());
			stm.setInt(4, contrato.getQtdParcelasCurso());
			stm.setBigDecimal(5, contrato.getValorMaterial());
			stm.setInt(6, contrato.getQtdParcelasMaterial());
			stm.setInt(7, contrato.getDiaVencimento());
			stm.setString(8, contrato.getFormaDePagamento());

			stm.setDate(9, new java.sql.Date(contrato.getDataMatricula().getTimeInMillis()));

			stm.setInt(10, contrato.getId());

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
}
