package br.com.franca.invicto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.FuncionarioDAO;
import br.com.franca.invicto.model.Funcionario;

@ManagedBean
@SessionScoped
public class FuncionarioBean extends CrudBean<Funcionario, FuncionarioDAO> {

	private FuncionarioDAO funcionarioDao;

	@Override
	public FuncionarioDAO getDao() {
		if (funcionarioDao == null) {
			funcionarioDao = new FuncionarioDAO();
		}
		return funcionarioDao;
	}

	@Override
	public Funcionario criarNovaEntidade() {
		return new Funcionario();
	}
}
