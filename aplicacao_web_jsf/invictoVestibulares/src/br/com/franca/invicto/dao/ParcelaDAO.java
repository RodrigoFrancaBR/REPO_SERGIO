package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Parcela;
import br.com.franca.invicto.model.Situacao;

public class ParcelaDAO implements CrudDAO<Parcela> {
	private List<Contrato> contratos;
	// Contrato contrato;

	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	@Override
	public Parcela salvar(Parcela entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Parcela entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Parcela entidade) {
		// TODO Auto-generated method stub

	}

	public Contrato buscarParcelasPorContrato(Contrato contrato) {
		Connection connection = new ConnectionFactory().getConnection();
		Parcela parcela;
		String sql = "SELECT * FROM TB_PARCELA WHERE CONTRATO_Id=?";

		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setInt(1, contrato.getId());
			rs = stm.executeQuery();

			while (rs.next()) {
				if (contrato.getId().equals(rs.getInt("contrato_id"))) {
					parcela = new Parcela();

					parcela.setId(rs.getInt("id_parcela"));

					java.sql.Date dataVencimentoSql = rs.getDate("data_vencimento");
					Calendar dataVencimentoCalendar = Calendar.getInstance();
					dataVencimentoCalendar.setTimeInMillis(dataVencimentoSql.getTime());
					parcela.setDataVencimento(dataVencimentoCalendar);

					parcela.setValorPago(rs.getBigDecimal("valor_pago"));

					java.sql.Date dataPagamentoSql = rs.getDate("data_pagamento");

					if (null == dataPagamentoSql) {
						parcela.setDataPagamento(null);
					} else {
						Calendar dataPagamentoCalendar = Calendar.getInstance();
						dataPagamentoCalendar.setTimeInMillis(dataPagamentoSql.getTime());
						parcela.setDataPagamento(dataPagamentoCalendar);
					}

					parcela.setValorDaParcelaDoCurso((rs.getBigDecimal("valor_parcela_curso")));

					parcela.setValorDaParcelaDoMaterial(rs.getBigDecimal("valor_parcela_material"));

					parcela.setValorTotalDaParcela(rs.getBigDecimal("valor_total_parcela"));

					parcela.setSituacao(Situacao.valueOf(rs.getInt("situacao_parcela")));

					contrato.getParcelas().add(parcela);
				}
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscarParcelasPorContrato(Contrato contrato)");
			e.printStackTrace();
			try {
				System.out.println("Tentando realizar o roolback");
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Ocorreu algum erro ao tentar realizar o roolback");
				e1.printStackTrace();
			}
		} finally {
			ConnectionFactory.closeAll(connection, stm, rs);
		}
		return contrato;
	}

	private Contrato getContrato(Integer id) {
		Contrato contrato = null;
		if (contratos.size() > 0) {
			for (Contrato c : contratos) {
				if (c.getId().equals(id)) {
					contrato = c;
					break;
				} else {
					continue;
					// return contrato;
				}
			}
			if (null == contrato) {
				contrato = new Contrato();
				contrato.setId(id);
			}
			return contrato;

		} else {
			contrato = new Contrato();
			contrato.setId(id);
			return contrato;
		}
	}

	public List<Contrato> buscarParcelas() {
		contratos = new ArrayList<>();
		Connection connection = new ConnectionFactory().getConnection();
		// List<Parcela> parcelas = new ArrayList<>();
		// List<Contrato> contratos = new ArrayList<>();
		Contrato contrato;

		Parcela parcela;

		String sql = "SELECT MATRICULA, P.* FROM TB_CONTRATO C, TB_PARCELA P WHERE C.ID_CONTRATO = P.CONTRATO_ID;";

		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);

			rs = stm.executeQuery();

			while (rs.next()) {
				contrato = null;
				contrato = getContrato(rs.getInt("contrato_id"));
				parcela = new Parcela();
				parcela.setId(rs.getInt("id_parcela"));

				java.sql.Date dataVencimentoSql = rs.getDate("data_vencimento");
				Calendar dataVencimentoCalendar = Calendar.getInstance();
				dataVencimentoCalendar.setTimeInMillis(dataVencimentoSql.getTime());
				parcela.setDataVencimento(dataVencimentoCalendar);

				parcela.setValorPago(rs.getBigDecimal("valor_pago"));

				java.sql.Date dataPagamentoSql = rs.getDate("data_pagamento");

				if (null == dataPagamentoSql) {
					parcela.setDataPagamento(null);
				} else {
					Calendar dataPagamentoCalendar = Calendar.getInstance();
					dataPagamentoCalendar.setTimeInMillis(dataPagamentoSql.getTime());
					parcela.setDataPagamento(dataPagamentoCalendar);
				}

				parcela.setValorDaParcelaDoCurso((rs.getBigDecimal("valor_parcela_curso")));

				parcela.setValorDaParcelaDoMaterial(rs.getBigDecimal("valor_parcela_material"));

				parcela.setValorTotalDaParcela(rs.getBigDecimal("valor_total_parcela"));

				parcela.setSituacao(Situacao.valueOf(rs.getInt("situacao_parcela")));

				contrato.getParcelas().add(parcela);

				if (contratos.indexOf(contrato) < 0) {
					contratos.add(contrato);
				}
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
		return contratos;
	}

	public List<Parcela> buscar() {
		return null;
	}

	public void salvarParcelas(Contrato contrato) {

		Connection connection = null;

		String sqlInsert = "INSERT INTO TB_PARCELA (contrato_id, data_vencimento, valor_pago, data_pagamento,"
				+ " valor_total_parcela, valor_parcela_curso, valor_parcela_material, situacao_parcela)"
				+ " values (?,?,?,?,?,?,?,?)";

		connection = new ConnectionFactory().getConnection();

		try {

			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlInsert);
			stm.setInt(1, contrato.getId());

			for (Parcela parcela : contrato.getParcelas()) {
				stm.setDate(2, new Date(parcela.getDataVencimento().getTimeInMillis()));
				stm.setBigDecimal(3, parcela.getValorPago());
				Date dataPagamento = (null == parcela.getDataPagamento()) ? null
						: new Date(parcela.getDataPagamento().getTimeInMillis());
				stm.setDate(4, dataPagamento);
				stm.setBigDecimal(5, parcela.getValorTotalDaParcela());
				stm.setBigDecimal(6, parcela.getValorDaParcelaDoCurso());
				stm.setBigDecimal(7, parcela.getValorDaParcelaDoMaterial());
				stm.setInt(8, parcela.getSituacao().getCodigo());

				linhas = stm.executeUpdate();
				connection.commit();
			}

		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no método salvarParcelas(Contrato contrato)");
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Ocorreu algum erro ao tentar realizar o rollback");
				e1.printStackTrace();
			}
		} finally {
			System.out.println("finaly");
		}

	}

}
