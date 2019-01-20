package br.com.franca.invicto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.franca.invicto.model.DespesaFixa;

public class DespesaFixaDAO implements CrudDAO<DespesaFixa> {
	private PreparedStatement stm;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private int linhas;
	
	@Override
	public void salvar(DespesaFixa entidade) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remover(DespesaFixa entidade) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<DespesaFixa> buscar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
