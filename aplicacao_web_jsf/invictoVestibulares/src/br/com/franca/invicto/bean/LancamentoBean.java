package br.com.franca.invicto.bean;

import java.util.Calendar;
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
	private List<Despesa> despesas;
	private Calendar dataInicio = Calendar.getInstance();
	private Calendar dataFinal = Calendar.getInstance();

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

	public void gerarLancamentos() {

		if (!getDao().temLancamentosGerados(dataInicio, dataFinal)) {

			int mesInicio = dataInicio.get(Calendar.MONTH);
			System.out.println(mesInicio);

			int mesFinal = dataFinal.get(Calendar.MONTH);
			System.out.println(mesFinal);

			// buscar despesas (categorias e funcionarios) ativas e que não são
			// do tipo variável
			List<Lancamento> lancamentos = getDao().buscarDespesasAtivasNaoVariaveis();

			getDao().gerarLancamentos(dataInicio, dataFinal, lancamentos);

			buscar();

			System.out.println("Lançamentos Gerados!");
		} else {
			System.out.println("Lançamentos não foram Gerados!");
		}
	}

	public List<Despesa> getDespesas() {
		DespesaDAO despesaDAO = new DespesaDAO();
		despesaDAO.buscarDespesasFixas();
		return despesas;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

}
