package br.com.franca.invicto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.CategoriaDAO;
import br.com.franca.invicto.model.Categoria;

@ManagedBean
@SessionScoped
public class CategoriaBean extends CrudBean<Categoria, CategoriaDAO> {
	private CategoriaDAO categoriaDAO;

	@Override
	public CategoriaDAO getDao() {
		if (categoriaDAO == null) {
			categoriaDAO = new CategoriaDAO();
		}
		return categoriaDAO;
	}

	@Override
	public Categoria criarNovaEntidade() {

		return new Categoria();
	}

}
