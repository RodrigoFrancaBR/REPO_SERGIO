package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.com.franca.invicto.dao.CrudDAO;
import br.com.franca.invicto.model.Despesa;
import br.com.franca.invicto.model.Situacao;

public abstract class CrudBean<E, D extends CrudDAO> {

	protected String estadoTela = "buscar";// Inserir, Editar, Buscar, simular

	protected E entidade;
	private List<E> entidades;

	private List <Situacao> situacoes;

	public void novo() {
		entidade = criarNovaEntidade();
		mudarParaInseri();
	}

	@SuppressWarnings("unchecked")
	public void salvar() {
		entidade = (E) getDao().salvar(entidade);
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
	
	
	public void onRowEdit(RowEditEvent event) {
		this.entidade = (E) event.getObject();
		getDao().alterar(entidade);
		FacesMessage msg = new FacesMessage("Atualizado com sucesso", ((E) event.getObject()).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancellada", ((E) event.getObject()).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
	
	public List<Situacao> getSituacoes() {
		if (this.situacoes == null) {
			this.situacoes = new ArrayList<>();
			situacoes.add(Situacao.ATIVA);
			situacoes.add(Situacao.DESATIVADA);
		}
		return situacoes;
	}
	
	public List<Situacao> getSituacoesAlunos() {
		if (this.situacoes == null) {
			this.situacoes = new ArrayList<>();
			situacoes.add(Situacao.ATIVO);
			situacoes.add(Situacao.DESATIVADO);
		}
		return situacoes;
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

	// Responsvel por criar os mÃ©todos nas classes bean
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
	
	public boolean isSimula() {
		return "simular".equals(estadoTela);
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
	
	public void mudarParaSimular() {
		estadoTela = "simular";
	}

}
