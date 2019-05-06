package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.AlunoDAO;
import br.com.franca.invicto.dao.ContratoDAO;
import br.com.franca.invicto.model.Aluno;
import br.com.franca.invicto.model.Contrato;

@ManagedBean
@SessionScoped
public class ContratoBean extends CrudBean<Contrato, ContratoDAO> {

	private ContratoDAO contratoDao;
	private Aluno aluno = new Aluno();
	private List <Integer>dias = new ArrayList<>();	        

	public List<Integer> getDias() {
		if (dias.isEmpty()) {
			for (int i = 1; i <= 31; i++) {
				dias.add(i);
			}
		}
		return dias;
	}
	
/*	public List<Integer> getDias() {		
		for (int i = 1 ; i <= 31 ; i++){
			dias.add(i);
		}
		return dias;
	}
*/
	@Override
	public ContratoDAO getDao() {
		if (contratoDao == null) {
			contratoDao = new ContratoDAO();
		}
		return contratoDao;
	}

	@Override
	public Contrato criarNovaEntidade() {
		return new Contrato();
	}

	public void consultar() {
		entidade.setAluno(new AlunoDAO().buscarPorCpf(entidade.getAluno().getCpf()));

	}

}
