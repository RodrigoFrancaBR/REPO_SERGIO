package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Parcela;
import br.com.franca.invicto.model.Situacao;

public class ParcelaDAO implements CrudDAO<Parcela> {

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

	@Override
	public List<Parcela> buscar() {
		// TODO Auto-generated method stub
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
				stm.setDate(4, new Date(parcela.getDataPagamento().getTimeInMillis()));
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
