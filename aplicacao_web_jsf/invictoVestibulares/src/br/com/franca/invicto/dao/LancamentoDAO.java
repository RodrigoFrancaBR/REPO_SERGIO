package br.com.franca.invicto.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public boolean temLancamentosGerados(Calendar dataInicio,Calendar dataFinal) {
		boolean temLancamentos = false;
		Connection connection = new ConnectionFactory().getConnection();
		//String sql = "SELECT * FROM TB_LANCAMENTO_DESPESA WHERE DATA_EMISSAO BETWEEN ? AND ? ";
		
		String sql = " SELECT LD.id_lancamento_despesa, LD.despesa_id, LD.data_vencimento, LD.valor_pago," +
		      " LD.data_pagamento, LD.situacao_lancamento, LD.data_emissao, D.id_despesa, D.categoria_id," +
			  " C.tipo_categoria FROM TB_LANCAMENTO_DESPESA AS LD, TB_DESPESA AS D, TB_CATEGORIA AS C" +
			  " WHERE DATA_EMISSAO BETWEEN ? AND ? AND LD.despesa_id = D.id_despesa AND D.categoria_id = C.id_categoria AND tipo_categoria <> 'Variável'; ";

		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);

			stm.setDate(1, new java.sql.Date(dataInicio.getTimeInMillis()));
			stm.setDate(2, new java.sql.Date(dataFinal.getTimeInMillis()));

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

	/*@Override
	public List<Lancamento> buscar() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		Lancamento lancamento;
		Despesa despesa;
		String sql = "SELECT * FROM TB_LANCAMENTO_DESPESA;";
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);			
			rs = stm.executeQuery();

			while (rs.next()) {
				lancamento = new Lancamento();
				despesa = new Despesa();
				lancamento.setId(rs.getInt("id_lancamento_despesa"));
				despesa.setId(rs.getInt("despesa_id"));

				java.sql.Date dataVencimentoSql = rs.getDate("data_vencimento");
				Calendar dataVencimentoCalendar = Calendar.getInstance();
				dataVencimentoCalendar.setTimeInMillis(dataVencimentoSql.getTime());
				lancamento.setDataVencimento(dataVencimentoCalendar);
				
				lancamento.setValorPago(rs.getBigDecimal("valor_pago"));
				
				java.sql.Date dataPagamentoSql = rs.getDate("data_pagamento");
				Calendar dataPagamentoCalendar = Calendar.getInstance();
				if (null!= dataPagamentoSql){
					dataPagamentoCalendar.setTimeInMillis(dataPagamentoSql.getTime());
					lancamento.setDataPagamento(dataPagamentoCalendar);	
				}
				
				
				lancamento.setSituacaoLancamento(rs.getString("situacao_lancamento"));
				
				java.sql.Date dataEmissaoSql = rs.getDate("data_emissao");
				Calendar dataEmissaoCalendar = Calendar.getInstance();
				dataEmissaoCalendar.setTimeInMillis(dataEmissaoSql.getTime());
				lancamento.setDataEmissao(dataEmissaoCalendar);
				
				lancamento.setDespesa(despesa);
				lancamentos.add(lancamento);
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
		return lancamentos;
	}*/


	@Override
	public void salvar(Lancamento entidade) {
		// TODO Auto-generated method stub
	}

	public void gerarLancamentos(Calendar dataInicio, Calendar dataFinal, List<Lancamento> lancamentos) {
		Connection connection = null;
		Calendar dataEmissao = Calendar.getInstance();
		Calendar dataVencimento = Calendar.getInstance();
		int diaVencimento = 0;
		int anoVencimento = 0;
		int anoInicio = dataInicio.get(Calendar.YEAR);
		int anoFinal = dataFinal.get(Calendar.YEAR);
		int mesInicio = dataInicio.get(Calendar.MONTH);
		int mesFinal = dataFinal.get(Calendar.MONTH);
		
		System.out.println(diaVencimento);
		System.out.println(anoVencimento);
		System.out.println(anoInicio);
		System.out.println(anoFinal);
		System.out.println(mesInicio);
		System.out.println(mesFinal);

		String sql = "INSERT INTO TB_LANCAMENTO_DESPESA (despesa_id, data_vencimento, valor_despesa, valor_pago, situacao_lancamento, data_emissao)"
				+ " values (?,?,?,?,?,?)";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			for (Lancamento lancamento : lancamentos) {
				// ano da data de inicio == ano da data fim
				if (anoInicio == anoFinal) {

					// recupera o ano da data inicio/fim
					anoVencimento = anoInicio; // podia ser o ano final tb

					// java janeiro 00 e dez 11
					for (int i = mesInicio ; i <= mesFinal; i++) {
						System.out.println("Passei por aqui!");
						stm.setInt(1, lancamento.getDespesa().getId());
						
						dataVencimento.set(anoVencimento, i, lancamento.getDespesa().getDataVencimento().get(Calendar.DAY_OF_MONTH));
						System.out.println(dataVencimento);
						stm.setDate(2, new java.sql.Date(dataVencimento.getTimeInMillis()));						
						
						stm.setBigDecimal(3, lancamento.getDespesa().getValorDespesa());
						
						stm.setBigDecimal(4, new BigDecimal("0.00"));
						
						stm.setString(5, "A Receber");
						
						stm.setDate(6, new java.sql.Date(dataEmissao.getTimeInMillis()));											
						

						linhas = stm.executeUpdate();

						connection.commit();

						final ResultSet rs = stm.getGeneratedKeys();
						if (rs.next()) {
							lancamento.setId(rs.getInt(1));
						}

						System.out.println("Lancamento salvo com sucesso!");
					}
				} else {
					throw new RuntimeException("O período deve contemplar apenas um ano!");
				}
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Ocorreu algum erro no metodo gerarLancamentos(List<Lancamento> lancamentos)");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			ConnectionFactory.closeAll(connection, stm, rs);
		}

	}

	public List<Lancamento> buscarDespesasAtivasNaoVariaveis() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		Despesa despesa;
		Categoria categoria;
		Funcionario funcionario;
		Lancamento lancamento;
						
				String sql = "select func.id_funcionario, func.matricula, func.nome, func.cargo, func.ativo,"
				+ " categ.id_categoria, categ.nome, categ.tipo_categoria, categ.ativo,"
				+ " desp.id_despesa, desp.data_vencimento, desp.valor_despesa, desp.via_recebido, desp.ativo"
				+ " from tb_despesa desp"
				+ " left join tb_funcionario func"
				+ " on func.id_funcionario = desp.funcionario_id"
				+ " join tb_categoria categ on categ.id_categoria = desp.categoria_id"
				+ " WHERE categ.tipo_categoria <> 'Variável' AND desp.ativo = 'Ativo'"
				+ " order by func.matricula";
		
		/*String sql = "SELECT id_despesa, funcionario_id, categoria_id, valor_despesa, data_vencimento, via_recebido, D.ativo,"
				+ " id_categoria, C.nome, tipo_categoria, C.ativo," + " id_funcionario, F.nome, matricula"
				+ " FROM TB_DESPESA AS D, TB_CATEGORIA AS C, TB_FUNCIONARIO AS F"
				+ " WHERE CATEGORIA_ID = ID_CATEGORIA AND" + " FUNCIONARIO_ID = ID_FUNCIONARIO AND"
				+ " D.ATIVO = 'Ativo' AND C.ATIVO = 'Ativo' AND F.ATIVO = 'Ativo' AND"
				+ " TIPO_CATEGORIA <> 'Variável';";*/

		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				despesa = new Despesa();
				categoria = new Categoria();
				funcionario = new Funcionario();
				lancamento = new Lancamento();
				
				funcionario.setId(rs.getInt(1));
				funcionario.setMatricula(rs.getString(2));
				funcionario.setNome(rs.getString(3));
				funcionario.setCargo(rs.getString(4));
				funcionario.setAtivo(rs.getString(5));
				
				categoria.setId(rs.getInt(6));
				categoria.setNome(rs.getString(7));
				categoria.setTipoCategoria(rs.getString(8));
				categoria.setAtivo(rs.getString(9));
				
				despesa.setId(rs.getInt(10));
				
				java.sql.Date dataSql = rs.getDate(11);
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());
								
				lancamento.setDataVencimento(dataCalendar);
				
				despesa.setValorDespesa((rs.getBigDecimal(12)));
				despesa.setViaRecebido(rs.getString(13));
				despesa.setAtivo(rs.getString(14));
				
				despesa.setCategoria(categoria);
				despesa.setFuncionario(funcionario);
				lancamento.setDespesa(despesa);
				lancamento.setSituacaoLancamento("Aguardando Pagamento");
				lancamento.setDataEmissao(Calendar.getInstance());
				lancamentos.add(lancamento);

				// despesas.add(despesa);

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
		return lancamentos;
	}
	
	public List<Lancamento> buscar() {
		Connection connection = new ConnectionFactory().getConnection();
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		Despesa despesa;
		Categoria categoria;
		Funcionario funcionario;
		Lancamento lancamento;
		String sql = "select func.id_funcionario, func.matricula, func.nome, func.cargo, func.ativo, " +
                "categ.id_categoria, categ.nome, categ.tipo_categoria, categ.ativo, " +
                "desp.id_despesa, desp.via_recebido, desp.ativo, " +
                "lanc.id_lancamento_despesa, lanc.data_vencimento, lanc.valor_despesa, " +
				"lanc.data_pagamento, lanc.valor_pago, " +
				"lanc.situacao_lancamento, lanc.data_emissao " +
                "from tb_lancamento_despesa lanc, tb_despesa desp " +
                "left join tb_funcionario func on func.id_funcionario = desp.funcionario_id " +
                "join tb_categoria categ on categ.id_categoria = desp.categoria_id " +
                "where   lanc.despesa_id = desp.id_despesa " +
                "order by func.matricula;";
	
		/*			
				String sql = "select func.id_funcionario, func.matricula, func.nome, func.cargo, func.ativo,"
				+ " categ.id_categoria, categ.nome, categ.tipo_categoria, categ.ativo,"
				+ " desp.id_despesa, desp.data_vencimento, desp.valor_despesa, desp.via_recebido, desp.ativo"
				+ " from tb_despesa desp"
				+ " left join tb_funcionario func"
				+ " on func.id_funcionario = desp.funcionario_id"
				+ " join tb_categoria categ on categ.id_categoria = desp.categoria_id"
				+ " WHERE categ.tipo_categoria <> 'Variável' AND desp.ativo = 'Ativo'"
				+ " order by func.matricula";*/
		//String sql = "SELECT * FROM TB_LANCAMENTO_DESPESA";
		
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				despesa = new Despesa();
				categoria = new Categoria();
				funcionario = new Funcionario();
				lancamento = new Lancamento();
				
				funcionario.setId(rs.getInt(1));
				funcionario.setMatricula(rs.getString(2));
				funcionario.setNome(rs.getString(3));
				funcionario.setCargo(rs.getString(4));
				funcionario.setAtivo(rs.getString(5));
				
				categoria.setId(rs.getInt(6));
				categoria.setNome(rs.getString(7));
				categoria.setTipoCategoria(rs.getString(8));
				categoria.setAtivo(rs.getString(9));
				
				despesa.setId(rs.getInt(10));
				despesa.setViaRecebido(rs.getString(11));
				despesa.setAtivo(rs.getString(12));
				
				lancamento.setId(rs.getInt(13));
				
				//despesa.setId(rs.getInt(2));
				
				java.sql.Date dataSql = rs.getDate(14);
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(dataSql.getTime());							
				lancamento.setDataVencimento(dataCalendar);
				
				lancamento.setValorDespesa((rs.getBigDecimal(15)));
			
				java.sql.Date dataSql2 = rs.getDate(16);
				
				if (null != dataSql2) {
					Calendar dataCalendar2 = Calendar.getInstance();
					dataCalendar2.setTimeInMillis(dataSql2.getTime());							
					lancamento.setDataPagamento(dataCalendar2);					
				}
				
				
				lancamento.setValorPago((rs.getBigDecimal(17)));
												
				lancamento.setSituacaoLancamento(rs.getString(18));
				
				java.sql.Date dataSql3 = rs.getDate(19);
				Calendar dataCalendar3 = Calendar.getInstance();
				dataCalendar3.setTimeInMillis(dataSql3.getTime());	
				
				lancamento.setDataEmissao(dataCalendar3);
				
				//despesa.setViaRecebido(rs.getString(13));
				//despesa.setAtivo(rs.getString(14));
				
				despesa.setCategoria(categoria);
				despesa.setFuncionario(funcionario);
				lancamento.setDespesa(despesa);
				lancamentos.add(lancamento);

				// despesas.add(despesa);

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
		return lancamentos;
	}
	
	
}
