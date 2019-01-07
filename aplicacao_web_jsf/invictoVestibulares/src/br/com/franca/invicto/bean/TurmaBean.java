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
	//private Unidade selectedUnidade;
	/*@PostConstruct
	public void inti() {
		unidades = new UnidadeDAO().buscar();
	}*/

	/*
	 * public void buscarUnidades() { unidades = new UnidadeDAO().buscar(); }
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
		
	/*public List<Unidade> getUnidades() {
		return new UnidadeDAO().buscar();
		
	}*/

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

/*	public Unidade getSelectedUnidade() {
		return selectedUnidade;
	}

	public void setSelectedUnidade(Unidade selectedUnidade) {
		this.selectedUnidade = selectedUnidade;
		 System.out.println("Unidade selecionada: " + selectedUnidade.getNome());  
	}*/

	/*
	 * private String nomeUnidade; private Turma turma = new Turma(); private
	 * static final long serialVersionUID = 1L;
	 * 
	 * public void gravar() { new TurmaController().gravar(turma); nomeUnidade =
	 * null; turma = new Turma(); }
	 * 
	 * public List<Turma> buscarTodos() { return new
	 * TurmaController().buscarTodos(); }
	 * 
	 * public void carregar(Turma turma) { this.turma = turma; }
	 * 
	 * public void remover(Turma turma) { new
	 * TurmaController().remover(turma.getNome()); turma = new Turma(); }
	 * 
	 * public Turma getTurma() { return turma; }
	 * 
	 * public void setTurma(Turma turma) { this.turma = turma; }
	 * 
	 * public void associarUnidade() { Unidade unidade = new
	 * UnidadeController().buscar(nomeUnidade); turma.setUnidade(unidade);
	 * System.out.println("Turma gerenciada pela unidade: " +
	 * unidade.getNome()); }
	 * 
	 * public String getUnidadeNome() { return nomeUnidade; }
	 * 
	 * public void setUnidadeNome(String nomeUnidade) { this.nomeUnidade =
	 * nomeUnidade; }
	 * 
	 * public List<Unidade> getUnidades() { return new
	 * UnidadeController().buscarTodos(); }
	 * 
	 * public void associarUnidade() { for (Unidade unidade : unidades) { if
	 * (unidade.getNome().equals(nomeUnidade)){ entidade } } Unidade unidade =
	 * new UnidadeController().buscar(nomeUnidade); turma.setUnidade(unidade);
	 * System.out.println("Turma gerenciada pela unidade: " +
	 * unidade.getNome()); }
	 * 
	 */
	
	
}
