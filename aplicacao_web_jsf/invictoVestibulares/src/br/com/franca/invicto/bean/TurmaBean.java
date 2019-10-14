package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.franca.invicto.dao.TurmaDAO;
import br.com.franca.invicto.dao.UnidadeDAO;
import br.com.franca.invicto.model.Turma;
import br.com.franca.invicto.model.Unidade;

@ManagedBean
@SessionScoped
public class TurmaBean extends CrudBean<Turma, TurmaDAO> {
	private TurmaDAO turmaDao;
	private List<Unidade> unidades;
	
	/* Para o dropdown de turmas ativas 
	 private List<Turma> turmasAtivas;	 
	 */
	
		
	public List<SelectItem> getUnidades() {  
	    List<SelectItem> items = new ArrayList<SelectItem>();  
	    unidades = new UnidadeDAO().buscar();
	    for (Unidade unidade : unidades) {  
	        // observem que o value do meu SelectItem é a própria entidade  
	        items.add(new SelectItem(unidade, unidade.getNome()));  
	    }  
	    return items;  
	}  

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	@Override
	public TurmaDAO getDao() {
		if (turmaDao == null) {
			turmaDao = new TurmaDAO();
		}
		return turmaDao;
	}

	@Override
	public Turma criarNovaEntidade() {
		return new Turma();
	}	
	
}
