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
import br.com.franca.invicto.model.CondicaoDoContrato;
import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.CursoAvistaMaterialParcelado;
import br.com.franca.invicto.model.CursoMaterialAvista;

public class ContratoDAO implements CrudDAO<Contrato> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public void salvar(Contrato contrato) {
		contrato.setMatricula(contrato.getDataMatricula().get(Calendar.YEAR)
				+ contrato.getAluno().getCpf());

		contrato.setSituacaoMatricula("Processo de Matrícula");		
		//CondicaoDoContrato condicao = contrato.getCondicaoDoContrato();
		contrato.setCondicaoDoContrato(contrato);

		Connection connection = null;
		String sqlInsert = "INSERT INTO TB_CONTRATO (taxa_matricula, valor_curso, desconto_curso, qtd_parcelas_curso, valor_material,"
				+ " qtd_parcelas_material, dia_vencimento, forma_pagamento, data_matricula, situacao_matricula, aluno_id, matricula, condicao_contrato)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			stm.setBigDecimal(1, contrato.getTaxaMatricula());
			stm.setBigDecimal(2, contrato.getValorCurso());
			stm.setDouble(3, contrato.getDescontoCurso());
			stm.setInt(4, contrato.getQtdParcelasCurso());
			stm.setBigDecimal(5, contrato.getValorMaterial());
			stm.setInt(6, contrato.getQtdParcelasMaterial());
			stm.setInt(7, contrato.getDiaVencimento());
			stm.setString(8, contrato.getFormaDePagamento());
			
			stm.setDate(9, new java.sql.Date(contrato.getDataMatricula()
					.getTimeInMillis()));
			stm.setString(10, contrato.getSituacaoMatricula());
			// stm.setString(10, contrato.getSituacaoMatricula());
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
			System.out
					.println("Ocorreu algum erro no metodo cadastrarcontrato(contrato contrato)");
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
				contrato.setQtdParcelasMaterial(rs
						.getInt("qtd_parcelas_material"));
				contrato.setDiaVencimento(rs.getInt("dia_vencimento"));
				contrato.setFormaDePagamento(rs.getString("forma_pagamento"));

				java.sql.Date dataSql = rs.getDate("data_matricula");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				contrato.setDataMatricula(dataCalendar);
				contrato.setSituacaoMatricula(rs
						.getString("situacao_matricula"));
				contrato.setCondicaoDoContrato(contrato);
				
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
			System.out
					.println("Ocorreu algum erro no metodo buscarTodos(Connection connection)");
			e.printStackTrace();
			// throw new RuntimeException(e);
			try {
				System.out.println("Tentando realizar o roolback");
				connection.rollback();
			} catch (SQLException e1) {
				System.out
						.println("Ocorreu algum erro ao tentar realizar o roolback");
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

/*		String sql = "SELECT * FROM TB_CONTRATO AS C, TB_ALUNO AS A WHERE C.ALUNO_ID = A.ID_ALUNO AND C.MATRICULA =? "
				+ "AND C.SITUACAO_MATRICULA =?;";*/
		String sql = "SELECT * FROM TB_CONTRATO AS C, TB_ALUNO AS A WHERE C.ALUNO_ID = A.ID_ALUNO AND "
				+ "C.MATRICULA =? AND C.SITUACAO_MATRICULA=?;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setString(1, matricula);
			stm.setString(2,"Processo de Matrícula" );
			rs = stm.executeQuery();

			if (rs.next()) {

				contrato = new Contrato();

				contrato.setId(rs.getInt("id_contrato"));
				contrato.setTaxaMatricula(rs.getBigDecimal("taxa_matricula"));
				contrato.setValorCurso(rs.getBigDecimal("valor_curso"));
				contrato.setDescontoCurso(rs.getDouble("desconto_curso"));
				contrato.setQtdParcelasCurso(rs.getInt("qtd_parcelas_curso"));
				// contrato.setQtdParcelasCurso(rs.getString("qtd_parcelas_curso"));
				contrato.setValorMaterial(rs.getBigDecimal("valor_material"));
				contrato.setQtdParcelasMaterial(rs
						.getInt("qtd_parcelas_material"));
				/*
				 * contrato.setQtdParcelasMaterial(rs
				 * .getString("qtd_parcelas_material"));
				 */
				contrato.setDiaVencimento(rs.getInt("dia_vencimento"));
				contrato.setFormaDePagamento(rs.getString("forma_pagamento"));

				java.sql.Date dataSql = rs.getDate("data_matricula");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				contrato.setDataMatricula(dataCalendar);
				contrato.setSituacaoMatricula(rs
						.getString("situacao_matricula"));
				contrato.setCondicaoDoContrato(contrato);
				
				/*String condicaoDoContrato = rs.getString("condicao_contrato");
				switch (condicaoDoContrato) {
				case "CursoMaterialAvista":
					contrato.setCondicaoDoContrato(new CursoMaterialAvista());
					break;
					
				case "CursoAvistaMaterialParcelado":
					contrato.setCondicaoDoContrato(new CursoAvistaMaterialParcelado());
					break;

				default:
					contrato.setCondicaoDoContrato(null);
					break;
				}
*/
				String matriculaEncontrada = rs.getString("matricula");

				String[] letras = { ".", "-" };

				for (String letra : letras) {
					matriculaEncontrada = matriculaEncontrada.replace(letra, "");
				}
				contrato.setMatricula(matriculaEncontrada);

				/* contrato.setMatricula(matricula.trim()); */

				contrato.getAluno().setId(rs.getInt("aluno_id"));

				contrato.getAluno().setNome(rs.getString("nome"));

				/* contrato.setMatricula(rs.getString ("matricula")); */

			}
		} catch (SQLException e) {
			System.out
					.println("Ocorreu algum erro no metodo buscarTodos(Connection connection)");
			e.printStackTrace();
			// throw new RuntimeException(e);
			try {
				System.out.println("Tentando realizar o roolback");
				connection.rollback();
			} catch (SQLException e1) {
				System.out
						.println("Ocorreu algum erro ao tentar realizar o roolback");
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

	/*
	 * public void salvar(Contrato contrato, Aluno aluno) {
	 * 
	 * Connection connection = null; String sqlInsert =
	 * "INSERT INTO TB_CONTRATO (taxa_matricula, valor_curso, desconto_curso, qtd_parcelas_curso, valor_material,"
	 * +
	 * " qtd_parcelas_material, dia_vencimento, forma_pagamento, data_matricula, situacao_matricula)"
	 * + " value (?,?,?,?,?,?,?,?,?,?)"; String sqlUpdateAluno =
	 * "UPDATE TB_ALUNO SET CONTRATO_ID = ?, MATRICULA=? WHERE ID_ALUNO=?";
	 * 
	 * try { connection = new ConnectionFactory().getConnection();
	 * connection.setAutoCommit(false); stm =
	 * connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
	 * 
	 * stm.setBigDecimal(1, contrato.getTaxaMatricula()); stm.setBigDecimal(2,
	 * contrato.getValorCurso()); stm.setDouble(3, contrato.getDescontoCurso());
	 * stm.setInt(4, contrato.getQtdParcelasCurso()); stm.setBigDecimal(5,
	 * contrato.getValorMaterial()); stm.setInt(6,
	 * contrato.getQtdParcelasMaterial()); stm.setInt(7,
	 * contrato.getDiaVencimento()); stm.setString(8,
	 * contrato.getFormaDePagamento()); stm.setDate(9, new
	 * java.sql.Date(contrato.getDataMatricula() .getTimeInMillis()));
	 * stm.setString(10, "Matriculado"); //stm.setString(10,
	 * contrato.getSituacaoMatricula());
	 * 
	 * linhas = stm.executeUpdate(); //connection.commit();
	 * System.out.println("contrato salva com sucesso!"); final ResultSet rs =
	 * stm.getGeneratedKeys(); if (rs.next()) { contrato.setId(rs.getInt(1)); }
	 * // configurar a matricula do aluno
	 * 
	 * aluno.setMatricula(contrato.getDataMatricula().get(Calendar.YEAR) +
	 * aluno.getNome().substring(0, 2) + aluno.getCpf());
	 * aluno.setContrato(contrato);
	 * 
	 * stm = connection.prepareStatement(sqlUpdateAluno); stm.setInt(1,
	 * contrato.getId()); stm.setString(2, aluno.getMatricula());
	 * stm.setInt(3,aluno.getId());
	 * 
	 * linhas = stm.executeUpdate(); connection.commit();
	 * System.out.println("aluno modificado com sucesso!");
	 * 
	 * } catch (Exception e) { try { connection.rollback(); } catch
	 * (SQLException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } System.out
	 * .println("Ocorreu algum erro no metodo cadastrarcontrato(contrato contrato)"
	 * ); e.printStackTrace(); throw new RuntimeException(e); } finally {
	 * 
	 * ConnectionFactory.closeAll(connection, stm, rs); }
	 * 
	 * }
	 */
}
