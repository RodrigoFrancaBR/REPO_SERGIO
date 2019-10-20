package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.franca.invicto.dao.CrudDAO;
import br.com.franca.invicto.model.Categoria;
import br.com.franca.invicto.model.Despesa;
import br.com.franca.invicto.model.Funcionario;
import br.com.franca.invicto.model.Unidade;

public class DespesaDAO implements CrudDAO<Despesa> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")

	private int linhas;

	@Override
	public Despesa salvar(Despesa despesa) {
		Connection connection = null;
		String sql = "INSERT INTO TB_DESPESA (categoria_id, funcionario_id, valor_despesa, data_vencimento, via_recebido, ativo)"
				+ " values (?,?,?,?,?,?)";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			if (null != despesa.getFuncionario().getId()) {
				stm.setInt(2, despesa.getFuncionario().getId());
			} else {
				stm.setNull(2, Types.INTEGER);
			}

			stm.setInt(1, despesa.getCategoria().getId());

			stm.setBigDecimal(3, despesa.getValorDespesa());
			stm.setDate(4, new java.sql.Date(despesa.getDataVencimento().getTimeInMillis()));
			stm.setString(5, despesa.getViaRecebido());
			stm.setString(6, "Ativo");

			linhas = stm.executeUpdate();

			despesa.setAtivo("Ativo");
			connection.commit();
			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				despesa.setId(rs.getInt(1));
			}

			System.out.println("Despesa salva com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo salvar(Despesa despesa)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}
		
		return despesa;

	}

	@Override
	public void alterar(Despesa despesa) {
		Connection connection = null;
		String sqlUpdate = "UPDATE TB_DESPESA SET categoria_id=?, funcionario_id=?, valor_despesa=?, data_vencimento=?, via_recebido=?, ativo=?"
				+ " WHERE ID_DESPESA=?";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlUpdate);

			stm.setInt(1, despesa.getCategoria().getId());

			if (null != despesa.getFuncionario().getId()) {
				stm.setInt(2, despesa.getFuncionario().getId());
			} else {
				stm.setNull(2, Types.INTEGER);
			}

			stm.setBigDecimal(3, despesa.getValorDespesa());
			stm.setDate(4, new java.sql.Date(despesa.getDataVencimento().getTimeInMillis()));
			stm.setString(5, despesa.getViaRecebido());
			stm.setString(6, despesa.getAtivo());
			stm.setInt(7, despesa.getId());

			linhas = stm.executeUpdate();

			connection.commit();

			System.out.println("Despesa alterada com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo alterar(Despesa despesa)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public void remover(Despesa despesa) {
		Connection connection = new ConnectionFactory().getConnection();
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement("UPDATE TB_DESPESA SET ativo =? WHERE id_despesa =?;");

			stm.setString(1, "Inativo");
			stm.setInt(2, despesa.getId());
			linhas = stm.executeUpdate();
			connection.commit();
			System.out.println("despesa removida com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo remover (Despesa despesa)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	@Override
	public List<Despesa> buscar() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Despesa> despesas = new ArrayList<Despesa>();

		Despesa despesa;
		Categoria categoria;
		Funcionario funcionario;

		String sql = "SELECT C.NOME, C.TIPO_CATEGORIA," + " F.NOME, F.MATRICULA, F.CARGO,"
				+ " D.ID_DESPESA, D.FUNCIONARIO_ID, D.CATEGORIA_ID, D.VALOR_DESPESA, D.DATA_VENCIMENTO, D.VIA_RECEBIDO, D.ATIVO"
				+ " FROM TB_DESPESA AS D" + " LEFT JOIN TB_FUNCIONARIO AS F ON D.FUNCIONARIO_ID = F.ID_FUNCIONARIO"
				+ " INNER JOIN TB_CATEGORIA AS C ON D.CATEGORIA_ID = C.ID_CATEGORIA;";

		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {

				categoria = new Categoria();
				categoria.setId(rs.getInt(8));
				categoria.setNome(rs.getString(1));
				categoria.setTipoCategoria((rs.getString(2)));

				funcionario = new Funcionario();

				if (categoria.getTipoCategoria().equals("Funcionário")) {
					funcionario.setId(rs.getInt(7));
					funcionario.setNome(rs.getString(3));
					funcionario.setMatricula(rs.getString(4));
					funcionario.setCargo(rs.getString(5));
				}

				despesa = new Despesa();
				despesa.setId(rs.getInt(6));
				despesa.setValorDespesa((rs.getBigDecimal(9)));
				despesa.setAtivo(rs.getString(12));

				java.sql.Date dataSql = rs.getDate(10);
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				despesa.setDataVencimento(dataCalendar);

				// despesa.setDiaVencimento(rs.getInt(10));
				despesa.setViaRecebido(rs.getString(11));

				despesa.setCategoria(categoria);
				despesa.setFuncionario(funcionario);
				despesas.add(despesa);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscar(Despesa despesa)");
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
		return despesas;
	}

	public void salvarDespesaVariavel(Despesa despesa) {
		Connection connection = null;
		String sql = "INSERT INTO TB_LANCAMENTO_DESPESA (despesa_id, data_vencimento, valor_pago, data_pagamento, situacao_lancamento, data_emissao, valor_despesa)"
				+ " values (?,?,?,?,?,?,?)";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stm.setInt(1, despesa.getId());
			stm.setDate(2, new java.sql.Date(despesa.getDataVencimento().getTimeInMillis()));
			stm.setBigDecimal(3, despesa.getValorDespesa());
			stm.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			stm.setString(5, "Recebido");
			stm.setDate(6, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			stm.setBigDecimal(7, despesa.getValorDespesa());
			linhas = stm.executeUpdate();

			despesa.setAtivo("Ativo");
			connection.commit();
			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				despesa.setId(rs.getInt(1));
			}

			System.out.println("Despesa salva com sucesso!");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo salvar(Despesa despesa)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	public void buscarDespesasFixas() {
		// TODO Auto-generated method stub

	}

}
