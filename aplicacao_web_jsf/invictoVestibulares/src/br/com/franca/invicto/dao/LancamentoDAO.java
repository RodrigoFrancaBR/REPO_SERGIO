package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.invicto.model.Categoria;
import br.com.franca.invicto.model.Despesa;
import br.com.franca.invicto.model.Funcionario;
import br.com.franca.invicto.model.Lancamento;

public class LancamentoDAO implements CrudDAO<Lancamento> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;

	public boolean temLancamentosGerados(Lancamento lancamento) {
		boolean temLancamentos = false;
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "SELECT * FROM TB_LANCAMENTO_DESPESA WHERE DATA_EMISSAO BETWEEN ? AND ? ";

		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);

			stm.setDate(1, new java.sql.Date(lancamento.getDataInicio().getTimeInMillis()));
			stm.setDate(2, new java.sql.Date(lancamento.getDataFim().getTimeInMillis()));

			rs = stm.executeQuery();

			if (rs.next()) {
				System.out.println("Não Permitir a geração dos Lancamentos");
				temLancamentos = true;
				// throw new SQLException("Já foram gerados os lancamentos esse
				// mês");
			} else {
				temLancamentos = false;
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
		return temLancamentos;
	}

	@Override
	public void alterar(Lancamento entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Lancamento entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Lancamento> buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Lancamento entidade) {
		// TODO Auto-generated method stub

	}

	public void gerarLancamentos(List<Despesa> despesas) {

	}

	public List <Despesa> buscarDespesasAtivasNaoVariaveis() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Despesa> despesas = new ArrayList<Despesa>();
		Despesa despesa;
		Categoria categoria;
		Funcionario funcionario;

		String sql = "SELECT id_despesa, funcionario_id, categoria_id, valor_despesa, data_vencimento, via_recebido, D.ativo,"
				+ " id_categoria, C.nome, tipo_categoria, C.ativo," + " id_funcionario, F.nome, matricula"
				+ " FROM TB_DESPESA AS D, TB_CATEGORIA AS C, TB_FUNCIONARIO AS F"
				+ " WHERE CATEGORIA_ID = ID_CATEGORIA AND" + " FUNCIONARIO_ID = ID_FUNCIONARIO AND"
				+ " D.ATIVO = 'Ativo' AND C.ATIVO = 'Ativo' AND F.ATIVO = 'Ativo' AND"
				+ " TIPO_CATEGORIA <> 'Variável';";

		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				despesa = new Despesa();
				categoria = new Categoria();
				funcionario = new Funcionario();
				despesa.setId(rs.getInt(1));
				funcionario.setId(rs.getInt(2));
				categoria.setId(rs.getInt(3));
				despesa.setValorDespesa((rs.getBigDecimal(4)));

				java.sql.Date dataSql = rs.getDate(5);
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
				despesa.setDataVencimento(dataCalendar);
				despesa.setViaRecebido(rs.getString(6));
				categoria.setNome(rs.getString(7));
				funcionario.setNome(rs.getString(8));
				funcionario.setMatricula(rs.getString(9));

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
}
