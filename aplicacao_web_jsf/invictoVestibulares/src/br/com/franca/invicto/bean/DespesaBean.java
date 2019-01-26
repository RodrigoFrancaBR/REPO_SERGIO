package br.com.franca.invicto.bean;

import java.util.ArrayList;
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
	private List <Integer>dias = new ArrayList<>();	

	@Override
	public DespesaDAO getDao() {
		if (despesaDao == null) {
			despesaDao = new DespesaDAO();
		}
		return despesaDao;
	}
	

	@Override
	public Despesa criarNovaEntidade() {
		return new Despesa();
	}
	
	   public void onRowEdit(RowEditEvent event) {
		   Despesa despesa =  (Despesa) event.getObject();		   
		   System.out.println(despesa.toString());
		   if (despesa.getCategoria().getTipoCategoria().equals("Variável")){
			   getDao().salvarDespesaVariavel(despesa);
			   FacesMessage msg = new FacesMessage("Despesa Variável cadastrada com sucesso", ((Despesa) event.getObject()).getCategoria().getNome());
			   FacesContext.getCurrentInstance().addMessage(null, msg);
				//buscar();
		   }else{
			   getDao().alterar(despesa);
			   FacesMessage msg = new FacesMessage("Despesa Fixa editada com sucesso", ((Despesa) event.getObject()).getCategoria().getNome());
			   FacesContext.getCurrentInstance().addMessage(null, msg);
				//buscar();
		   }	        	        	        
	    }
	     
	    public void onRowCancel(RowEditEvent event) {
	        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Despesa) event.getObject()).getCategoria().getNome());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	    
	
	
	public void editarDespesa(Despesa despesa) {
		if (despesa.getCategoria().getTipoCategoria().equals("Variável")){
			mudarParaSalvarDespesaVariavel();	
		}else{
			mudarParaEdita();
		}
		this.entidade = despesa;		
	}
	
	private void mudarParaSalvarDespesaVariavel() {
		estadoTela = "salvarDespesaVariável";
	}
	

	public boolean isSalvarDespesaVariavel() {
		return "salvarDespesaVariável".equals(estadoTela);
	}
	
	
	public void salvarDespesa() {		
		getDao().salvarDespesaVariavel(entidade);	
		buscar();
	}


	public List<Integer> getDias() {
		for (int i = 1 ; i <= 31 ; i++){
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
		return new CategoriaDAO().buscar();
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

}
