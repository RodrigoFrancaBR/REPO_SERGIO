package br.com.franca.invicto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.UnidadeDAO;
import br.com.franca.invicto.model.Situacao;
import br.com.franca.invicto.model.Unidade;

@ManagedBean
@SessionScoped
public class UnidadeBean extends CrudBean<Unidade, UnidadeDAO> {
	private UnidadeDAO unidadeDao;

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

	public Situacao[] getSituacoes() {		
		
		return Situacao.values();
	}

}
