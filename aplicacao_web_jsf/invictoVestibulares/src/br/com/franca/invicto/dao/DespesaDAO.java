package br.com.franca.invicto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public void salvar(Despesa despesa) {
		Connection connection = null;
		String sqlInsert = "INSERT INTO TB_DESPESA (categoria_id, funcionario_id, valorDespesa, diaVencimento, viaRecebido, ativo)"
				+ " values (?,?,?,?,?,?)";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, despesa.getCategoria().getNome());
			stm.setString(2, despesa.getFuncionario().getNome());
			stm.setBigDecimal(3, despesa.getValorDespesa());
			stm.setInt(4, despesa.getDiaVencimento());
			stm.setString(5, despesa.getViaRecebido());
			stm.setBoolean(6, true);

			linhas = stm.executeUpdate();

			despesa.setAtivo(true);
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

	@Override
	public void alterar(Despesa despesa) {
		Connection connection = null;
		String sqlUpdate = "UPDATE TB_DESPESA SET categoria_id=?, funcionario_id=?, valorDespesa=?, diaVencimento=?, viaRecebido=?"
				+ "WHERE ID_DESPESA=?";

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sqlUpdate);

			stm.setString(1, despesa.getCategoria().getNome());
			stm.setString(2, despesa.getFuncionario().getNome());
			stm.setBigDecimal(3, despesa.getValorDespesa());
			stm.setInt(4, despesa.getDiaVencimento());
			stm.setString(5, despesa.getViaRecebido());
			stm.setInt(6, despesa.getId());

			linhas = stm.executeUpdate();

			
			connection.commit();
						
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

	@Override
	public void remover(Despesa despesa) {
		Connection connection = new ConnectionFactory().getConnection();
		try {
			connection.setAutoCommit(false);
			stm = connection.prepareStatement("UPDATE TB_DESPESA SET ativo =? WHERE id_despesa =?;");

			stm.setBoolean(1, false);
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
		
		String sql = "SELECT" +
				" C.NOME, C.TIPO_CATEGORIA," +
				" F.NOME, F.MATRICULA, F.CARGO," +
				" D.ID_DESPESA, D.FUNCIONARIO_ID, D.CATEGORIA_ID, D.VALOR_DESPESA, D.DIA_VENCIMENTO, D.VIA_RECEBIDO" +		
				" FROM"+
				" TB_CATEGORIA AS C," +
				" TB_FUNCIONARIO AS F," +
				" TB_DESPESA AS D" +
				" WHERE" +
				" D.CATEGORIA_ID = C.ID_CATEGORIA" +
				" AND" +
				" D.FUNCIONARIO_ID = F.ID_FUNCIONARIO" +
				" AND" + 
				" D.ATIVO =1;";
		
		try {		
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(sql);
			stm.setBoolean(1, true);
			rs = stm.executeQuery();

			while (rs.next()) {
				
				despesa = new Despesa();				
				despesa.setId(rs.getInt("D.ID_DESPESA"));
				
				categoria = new Categoria();
				categoria.setId(rs.getInt("D.CATEGORIA_ID"));
				categoria.setNome(rs.getString("C.NOME"));
				categoria.setTipoCategoria((rs.getString("C.TIPO_CATEGORIA")));
				
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("D.FUNCIONARIO_ID"));
				funcionario.setNome(rs.getString("F.NOME"));
				funcionario.setNome(rs.getString("F.MATRICULA"));
				funcionario.setNome(rs.getString("F.CARGO"));
				
				despesa = new Despesa();
				despesa.setId(rs.getInt("D.ID_DESPESA"));
				despesa.setValorDespesa((rs.getBigDecimal("D.VALOR_DESPESA")));
				despesa.setDiaVencimento(rs.getInt("D.DIA_VENCIMENTO"));
				despesa.setViaRecebido(rs.getString("D.VIA_RECEBIDO"));				
				
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
		return unidades;
	}

}
