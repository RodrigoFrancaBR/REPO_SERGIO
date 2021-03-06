package br.com.franca.invicto.dao;

import java.util.List;

public interface CrudDAO<E> {
	
	public E salvar(E entidade);
	
	public void alterar (E entidade);

	public void remover(E entidade);

	public List<E> buscar();

}
