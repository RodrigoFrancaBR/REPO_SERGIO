package br.com.franca.invicto.bean;

import java.util.List;

import br.com.franca.invicto.dao.CrudDAO;

public abstract class CrudBean<E, D extends CrudDAO> {

	protected String estadoTela = "buscar";// Inserir, Editar, Buscar

	protected E entidade;
	private List<E> entidades;

	public void novo() {
		entidade = criarNovaEntidade();
		mudarParaInseri();
	}
	
	@SuppressWarnings("unchecked")
	public void salvar() {		
		getDao().salvar(entidade);	
		buscar();
	}
	

	@SuppressWarnings("unchecked")
	public void alterar() {		
		getDao().alterar(entidade);	
		buscar();
	}

	public void editar(E entidade) {
		this.entidade = entidade;
		mudarParaEdita();
	}
	
	@SuppressWarnings("unchecked")
	public void remover(E entidade) {
		getDao().remover(entidade);
		entidades.remove(entidade);
	}
	
	@SuppressWarnings("unchecked")
	public void buscar() {		
		if (isBusca() == false) {
			mudarParaBusca();			
		}
		
		entidades = getDao().buscar();
		return;
	}	

	// getters e setters
	public E getEntidade() {
		return entidade;
	}

	public void setEntidade(E entidade) {
		this.entidade = entidade;
	}

	public List<E> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<E> entidades) {
		this.entidades = entidades;
	}

	// Responsvel por criar os métodos nas classes bean
	public abstract D getDao();

	public abstract E criarNovaEntidade();

	// Metodos para controle da tela
	public boolean isInseri() {
		return "inserir".equals(estadoTela);
	}

	public boolean isEdita() {
		return "editar".equals(estadoTela);
	}

	public boolean isBusca() {
		return "buscar".equals(estadoTela);
	}
	

	public void mudarParaInseri() {
		estadoTela = "inserir";
	}

	public void mudarParaEdita() {
		estadoTela = "editar";
	}

	public void mudarParaBusca() {
		estadoTela = "buscar";
	}
	
}
