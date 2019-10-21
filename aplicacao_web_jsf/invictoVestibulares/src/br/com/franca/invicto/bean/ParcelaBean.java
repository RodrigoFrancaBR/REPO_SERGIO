package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.ParcelaDAO;
import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Parcela;

@ManagedBean
@SessionScoped
public class ParcelaBean extends CrudBean<Parcela, ParcelaDAO> {
	private ParcelaDAO parcelaDAO;
	private List<Contrato> contratos = new ArrayList<>();

	@Override
	public ParcelaDAO getDao() {
		if (parcelaDAO == null) {
			parcelaDAO = new ParcelaDAO();
		}
		return parcelaDAO;
	}

	public ParcelaBean() {
		if (null == parcelaDAO) {
			parcelaDAO = new ParcelaDAO();
		}
	}

	@Override
	public Parcela criarNovaEntidade() {
		return new Parcela();
	}

	public void buscarParcelas() {
		contratos = parcelaDAO.buscarParcelas();		
	}
	
	public List<Contrato> getContratos(){
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	
	
}
