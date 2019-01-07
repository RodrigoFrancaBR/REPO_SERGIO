package br.com.franca.invicto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.UnidadeDAO;
import br.com.franca.invicto.model.Unidade;

@ManagedBean
@SessionScoped
public class UnidadeBean extends CrudBean<Unidade, UnidadeDAO> {
	private UnidadeDAO unidadeDao;

	/*@PostConstruct
	public void inti() {
		System.out.println("Passei por aqui primeiro");
		buscar();
	}*/

	@Override
	public UnidadeDAO getDao() {
		if (unidadeDao == null) {
			unidadeDao = new UnidadeDAO();
		}
		return unidadeDao;
	}

	@Override
	public Unidade criarNovaEntidade() {
		return new Unidade();
	}

	/*
	 * private Unidade unidade;
	 * 
	 * @PostConstruct public void init() { System.out.println("init"); unidade =
	 * new Unidade(); }
	 * 
	 * public void gravar() { new UnidadeController().gravar(unidade); init(); }
	 * 
	 * public String buscar() { System.out.println("teste"); Unidade unidade;
	 * unidade = new UnidadeController().buscar(this.unidade); this.unidade =
	 * unidade; return null; }
	 * 
	 * public List<Unidade> buscarTodos() { return new
	 * UnidadeController().buscarTodos(); }
	 * 
	 * public void carregar(Unidade unidade) { this.unidade = unidade; }
	 * 
	 * public void remover(Unidade unidade) { new
	 * UnidadeController().remover(unidade.getNome()); }
	 * 
	 * public Unidade getUnidade() { return unidade; }
	 * 
	 * public void setUnidade(Unidade unidade) { this.unidade = unidade; }
	 */
}
