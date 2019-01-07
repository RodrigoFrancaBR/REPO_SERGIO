package br.com.franca.invicto.dao;

import java.util.List;

public interface CrudDAO<E> {
	
	public void salvar(E entidade);

	public void remover(E entidade);

	public List<E> buscar();

}
