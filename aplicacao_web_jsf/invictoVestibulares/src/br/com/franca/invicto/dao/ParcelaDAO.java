package br.com.franca.invicto.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Parcela;

public class ParcelaDAO implements CrudDAO<Parcela> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public void salvar(Parcela parcela) {

		for (Parcela p : parcela.getContrato().getParcelas()) {

			Connection connection = null;
			String sqlInsert = "INSERT INTO TB_PARCELA (contrato_id, numero_parcela_curso, numero_parcela_material,"
					+ " data_vencimento, valor_pago, data_pagamento, valor_parcela_curso, valor_parcela_material,"
					+ " valor_total_parcela, situacao_parcela, numero_parcela, taxa_matricula, valor_residual_curso, valor_residual_material)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			String sqlUpdate = "UPDATE TB_CONTRATO SET SITUACAO_MATRICULA=? WHERE ID_CONTRATO=?";

			try {
				connection = new ConnectionFactory().getConnection();
				connection.setAutoCommit(false);

				stm = connection.prepareStatement(sqlInsert);
				stm.setBoolean(3, true);

				stm.setInt(1, parcela.getContrato().getId());
				stm.setInt(2, p.getNumeroDaParcelaCurso());
				stm.setInt(3, p.getNumeroDaParcelaMaterial());
				stm.setDate(4, new java.sql.Date(p.getDataVencimento()
						.getTimeInMillis()));
				stm.setBigDecimal(5, p.getValorPago());

				if (null == p.getDataPagamento()) {
					stm.setDate(6, null);
				} else {
					stm.setDate(6, new java.sql.Date(p.getDataPagamento()
							.getTimeInMillis()));
				}

				stm.setBigDecimal(7, p.getValorDaParcelaDoCurso());
				stm.setBigDecimal(8, p.getValorDaParcelaDoMaterial());
				// stm.setBigDecimal(9,
				// parcela.getContrato().getTaxaMatricula());
				stm.setBigDecimal(9, p.getValorTotalDaParcela());
				stm.setString(10, p.getSituacaoDaParcela());
				stm.setInt(11, p.getNumeroDaParcela());
				stm.setBigDecimal(12, p.getTaxaMatricula());
				stm.setBigDecimal(13, p.getValorResidualDaParcelaCurso());
				stm.setBigDecimal(14, p.getValorResidualDaParcelaMaterial());

				linhas = stm.executeUpdate();
				System.out.println("parcela salva com sucesso!");

				stm = connection.prepareStatement(sqlUpdate);
				stm.setString(1, "Matriculado");
				// stm.setInt(2,p.getContrato().getId());
				stm.setInt(2, parcela.getContrato().getId());
				linhas = stm.executeUpdate();
				System.out.println("Contrato atualizado com sucesso!");

				connection.commit();

				System.out.println("parcela salva com sucesso!");
			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out
						.println("Ocorreu algum erro no metodo cadastrarparcela(parcela parcela)");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			finally {

				ConnectionFactory.closeAll(connection, stm, rs);
			}
		}
	}

	@Override
	public void remover(Parcela parcela) {

	}

	@Override
	public List<Parcela> buscar() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Parcela> parcelas = new ArrayList<Parcela>();
		Parcela parcela;
		Contrato contrato;
		String sql = "SELECT * FROM TB_PARCELA AS P, TB_CONTRATO AS C, TB_ALUNO AS A WHERE P.CONTRATO_ID = C.ID_CONTRATO AND C.ALUNO_ID = A.ID_ALUNO;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			// stm.setBoolean(1, true);
			rs = stm.executeQuery();

			while (rs.next()) {

				contrato = new Contrato();
				contrato.setId(rs.getInt("id_contrato"));
				contrato.setTaxaMatricula(rs.getBigDecimal("taxa_matricula"));
				contrato.setValorCurso(rs.getBigDecimal("valor_curso"));
				contrato.setDescontoCurso(rs.getDouble("desconto_curso"));
				contrato.setQtdParcelasCurso(rs.getInt("qtd_parcelas_curso")); //
				contrato.setValorMaterial(rs.getBigDecimal("valor_material"));
				contrato.setQtdParcelasMaterial(rs
						.getInt("qtd_parcelas_material"));
				contrato.setDiaVencimento(rs.getInt("dia_vencimento"));
				contrato.setFormaDePagamento(rs.getString("forma_pagamento"));

				java.sql.Date dataMatriculaSql = rs.getDate("data_matricula");
				Calendar dataMatriculaCalendar = Calendar.getInstance();
				dataMatriculaCalendar.setTimeInMillis(dataMatriculaSql
						.getTime());
				contrato.setDataMatricula(dataMatriculaCalendar);

				contrato.setSituacaoMatricula(rs
						.getString("situacao_matricula"));
				contrato.setCondicaoDoContrato(contrato);

				String matricula = rs.getString("matricula");

				String[] letras = { ".", "-" };

				for (String letra : letras) {
					matricula = matricula.replace(letra, "");
				}
				contrato.setMatricula(matricula);

				contrato.getAluno().setId(rs.getInt("aluno_id"));

				contrato.getAluno().setNome(rs.getString("nome"));

				parcela = new Parcela();

				parcela.setId(rs.getInt("id_parcela"));
				parcela.getContrato().setId(rs.getInt("id_parcela"));
				parcela.setNumeroDaParcelaCurso(rs
						.getInt("numero_parcela_curso"));
				parcela.setNumeroDaParcelaMaterial(rs
						.getInt("numero_parcela_material"));

				java.sql.Date dataVencimentoSql = rs.getDate("data_vencimento");
				Calendar dataVencimentoCalendar = Calendar.getInstance();
				dataVencimentoCalendar.setTimeInMillis(dataVencimentoSql
						.getTime());
				parcela.setDataVencimento(dataVencimentoCalendar);

				parcela.setValorPago(rs.getBigDecimal("valor_pago"));

				java.sql.Date dataPagamentoSql = rs.getDate("data_pagamento");

				if (null == dataPagamentoSql) {
					parcela.setDataPagamento(null);
				} else {
					Calendar dataPagamentoCalendar = Calendar.getInstance();
					dataPagamentoCalendar.setTimeInMillis(dataPagamentoSql
							.getTime());
					parcela.setDataPagamento(dataPagamentoCalendar);
				}

				parcela.setValorDaParcelaDoCurso((rs
						.getBigDecimal("valor_parcela_curso")));
				parcela.setValorDaParcelaDoMaterial(rs
						.getBigDecimal("valor_parcela_material"));
				parcela.getContrato().setTaxaMatricula(
						rs.getBigDecimal("taxa_matricula"));
				parcela.setValorTotalDaParcela(rs
						.getBigDecimal("valor_total_parcela"));

				parcela.setSituacaoDaParcela((rs.getString("situacao_parcela")));
				parcela.setNumeroDaParcela(rs.getInt("numero_parcela"));
				parcela.setValorResidualDaParcelaCurso((rs
						.getBigDecimal("valor_residual_curso")));
				parcela.setValorResidualDaParcelaMaterial((rs
						.getBigDecimal("valor_residual_material")));

				parcela.setContrato(contrato);
				parcelas.add(parcela);
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
		return parcelas;
	}

	public void receberPagamento(Parcela parcela) {
		Connection connection = null;
		String sqlUpdate = "UPDATE TB_PARCELA SET SITUACAO_PARCELA=?, DATA_PAGAMENTO =?, VALOR_PAGO=? WHERE ID_PARCELA =?;";
		String situacaoParcela = "Pago";
		Calendar dataPagamento = Calendar.getInstance();
		BigDecimal valorPago = (parcela.getValorTotalDaParcela());
		
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stm = connection.prepareStatement(sqlUpdate);
			stm.setString(1, situacaoParcela);						
			stm.setDate(2, new java.sql.Date(dataPagamento					
					.getTimeInMillis()));			
			stm.setBigDecimal(3, valorPago);
			stm.setInt(4, parcela.getId());
			
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("Parcela recebida com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out
					.println("Ocorreu algum erro no metodo receberPagamento(Parcela parcela)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}
}
