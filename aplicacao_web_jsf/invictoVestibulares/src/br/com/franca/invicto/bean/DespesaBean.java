package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.CategoriaDAO;
import br.com.franca.invicto.dao.DespesaDAO;
import br.com.franca.invicto.dao.FuncionarioDAO;
import br.com.franca.invicto.model.Categoria;
import br.com.franca.invicto.model.Despesa;
import br.com.franca.invicto.model.Funcionario;

@ManagedBean
@SessionScoped
public class DespesaBean extends CrudBean<Despesa, DespesaDAO> {
	private DespesaDAO despesaDao;
	private List<Funcionario> funcionarios;
	private List <Integer>dias = new ArrayList<>();	

	@Override
	public DespesaDAO getDao() {
		if (despesaDao == null) {
			despesaDao = new DespesaDAO();
		}
		return despesaDao;
	}

	@Override
	public Despesa criarNovaEntidade() {
		return new Despesa();
	}
	
	public List<Integer> getDias() {
		for (int i = 1 ; i <= 31 ; i++){
			dias.add(i);
		}
		return dias;
	}

	public void onCategoriaChange() {
		System.out.println("Passei por aqui");
		if (null != entidade.getCategoria().getId() && !entidade.getCategoria().getId().equals("")) {
			Categoria categoria = new CategoriaDAO().buscarPor(entidade.getCategoria().getId());
			entidade.setCategoria(categoria);
			if (categoria.getTipoCategoria().equals("Funcionário")) {
				funcionarios = new FuncionarioDAO().buscar();
			} else {
				funcionarios = new ArrayList<>();
			}
		}
	}
  
	public List<Categoria> getCategorias() {
		return new CategoriaDAO().buscar();
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

}
