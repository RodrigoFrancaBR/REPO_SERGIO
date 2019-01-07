package br.com.franca.invicto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.franca.invicto.dao.AlunoDAO;
import br.com.franca.invicto.dao.TurmaDAO;
import br.com.franca.invicto.model.Aluno;
import br.com.franca.invicto.model.Turma;

@ManagedBean
@SessionScoped
public class AlunoBean extends CrudBean<Aluno, AlunoDAO> {

	private AlunoDAO alunoDao;
	private List<Turma> turmas;
	Aluno aluno = new Aluno();
	
	@Override
	public AlunoDAO getDao() {
		if (alunoDao == null) {
			alunoDao = new AlunoDAO();
		}
		return alunoDao;
	}

	@Override
	public Aluno criarNovaEntidade() {
		return new Aluno();
	}

	public List<Turma> getTurmas() {
		return new TurmaDAO().buscar();
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}
