package br.com.franca.invicto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.DespesaDAO;
import br.com.franca.invicto.dao.LancamentoDAO;
import br.com.franca.invicto.model.Despesa;
import br.com.franca.invicto.model.Lancamento;

@ManagedBean
@SessionScoped
public class LancamentoBean extends CrudBean<Lancamento, LancamentoDAO> {
	private LancamentoDAO lancamentoDao;
	private List <Despesa> despesas;

	@Override
	public LancamentoDAO getDao() {
		if (lancamentoDao == null) {
			lancamentoDao = new LancamentoDAO();
		}
		return lancamentoDao;
	}

	@Override
	public Lancamento criarNovaEntidade() {
		return new Lancamento();
	}
	
	public void gerarLancamentos(){
		if (!getDao().temLancamentosGerados(entidade)){
			// recuperar o mês do lancamento para fazer o inicio do for e o mes final do lancamento para o fim do for
			// enquanto estiver dentro do periodo inserir os lancamentos
			 int mesInicio = entidade.getDataInicio().MONTH;
			 System.out.println(mesInicio);
			//lancamentoDao.gerarLancamentos(lancamentos);
			System.out.println("Lançamentos Gerados!");	
		}else{
			System.out.println("Lançamentos não foram Gerados!");
		}				
	}

	public List<Despesa> getDespesas() {
		DespesaDAO despesaDAO = new DespesaDAO();
		despesaDAO.buscarDespesasFixas();
		return despesas;
	}
	
	

}
