package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.com.franca.invicto.dao.CategoriaDAO;
import br.com.franca.invicto.dao.DespesaDAO;
import br.com.franca.invicto.dao.FuncionarioDAO;
import br.com.franca.invicto.model.Categoria;
import br.com.franca.invicto.model.Despesa;
import br.com.franca.invicto.model.Funcionario;

@ManagedBean
@SessionScoped
public class DespesaBean extends CrudBean<Despesa, DespesaDAO> {
	private DespesaDAO despesaDao;
	private List<Funcionario> funcionarios;
	private List<Integer> dias = new ArrayList<>();

	@Override
	public DespesaDAO getDao() {
		if (despesaDao == null) {
			despesaDao = new DespesaDAO();
		}
		return despesaDao;
	}

	@Override
	public Despesa criarNovaEntidade() {
		System.out.println();
		return new Despesa();
	}

	public void onRowEdit(RowEditEvent event) {
		Despesa despesa = (Despesa) event.getObject();

		getDao().alterar(despesa);
		FacesMessage msg = new FacesMessage("Despesa editada com sucesso",
				((Despesa) event.getObject()).getCategoria().getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edi��o Cancellada",
				((Despesa) event.getObject()).getCategoria().getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void editarDespesa(Despesa despesa) {
		if (despesa.getCategoria().getTipoCategoria().equals("Variável")) {
			mudarParaSalvarDespesaVariavel();
		} else {
			mudarParaEdita();
		}
		this.entidade = despesa;
	}

	private void mudarParaSalvarDespesaVariavel() {
		estadoTela = "salvarDespesaVari�vel";
	}

	public boolean isSalvarDespesaVariavel() {
		return "salvarDespesaVariável".equals(estadoTela);
	}

	public void salvarDespesa(Despesa despesa) {
		getDao().salvarDespesaVariavel(despesa);
		buscar();
	}

	public List<Integer> getDias() {
		for (int i = 1; i <= 31; i++) {
			dias.add(i);
		}
		return dias;
	}

	public void onCategoriaChange() {
		System.out.println("Passei por aqui");
		if (null != entidade.getCategoria().getId() && !entidade.getCategoria().getId().equals("")) {
			Categoria categoria = new CategoriaDAO().buscarPor(entidade.getCategoria().getId());
			entidade.setCategoria(categoria);
			if (categoria.getTipoCategoria().equals("Funcionário")) {
				funcionarios = new FuncionarioDAO().buscar();
			} else {
				funcionarios = new ArrayList<>();
			}
		}
	}

	public List<Categoria> getCategorias() {
		return new CategoriaDAO().buscarAtivos();
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void gerarDespesas() {
		System.out.println("Gerando Despesas!");
		List<Despesa> despesasFixas = new ArrayList<>();
		
		java.sql.Date dataInicio, dataFinal;
		
		// Definir periodo inicial dos lancamentos e periodo final dos lancamentos  
		Calendar c = Calendar.getInstance();
		int dias = c.getActualMaximum(Calendar.DAY_OF_MONTH); //  retorna o m�ximo de dias do m�s atual
		
		Calendar cInicio = Calendar.getInstance(); // nova data atual 
		cInicio.set(cInicio.get(Calendar.YEAR), cInicio.get(Calendar.MONTH), 1); // configuro a data atual para ano/m�s/dia 1 atual

		dataInicio = new java.sql.Date(cInicio.getTimeInMillis()); // converter calendar para dataUtil
		

		Calendar cFinal = Calendar.getInstance();
		cFinal.set(cFinal.get(Calendar.YEAR), cFinal.get(Calendar.MONTH),
				cFinal.getActualMaximum(Calendar.DAY_OF_MONTH));
		dataFinal = new java.sql.Date(cFinal.getTimeInMillis());
		
		
		System.out.println("Dias: " + dias);

		
		/*for (Despesa despesa : getEntidades()) {
			System.out.println(despesa.toString());
			if (null != despesa) {
				if (!despesa.getCategoria().getTipoCategoria().equals("Vari�vel"))
					despesasFixas.add(despesa);					
			}
		}			*/
	}

}
