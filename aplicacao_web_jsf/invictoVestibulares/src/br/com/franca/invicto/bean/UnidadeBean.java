package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.UnidadeDAO;
import br.com.franca.invicto.model.Situacao;
import br.com.franca.invicto.model.Unidade;

@ManagedBean
@SessionScoped
public class UnidadeBean extends CrudBean<Unidade, UnidadeDAO> {
	private UnidadeDAO unidadeDao;
	// private List<Situacao> situacoes;

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
	
//	public List<Situacao> getSituacoes() {
//		if (this.situacoes == null) {
//			this.situacoes = new ArrayList<>();
//			situacoes.add(Situacao.ATIVA);
//			situacoes.add(Situacao.DESATIVADA);
//		}
//		return situacoes;
//	}

}
