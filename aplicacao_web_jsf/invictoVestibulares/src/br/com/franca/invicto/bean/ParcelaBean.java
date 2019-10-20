package br.com.franca.invicto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.ParcelaDAO;
import br.com.franca.invicto.model.Parcela;

@ManagedBean
@SessionScoped
public class ParcelaBean extends CrudBean<Parcela, ParcelaDAO> {

	@Override
	public ParcelaDAO getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parcela criarNovaEntidade() {
		// TODO Auto-generated method stub
		return null;
	}

}
