package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	//private Map <String, Turma> turmas; 
	private List<String> turnos;
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
		return turmas = new TurmaDAO().buscar();
				
	}

	/*public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}*/

	public void onTurmaChange() {
		if (entidade.getTurma().getId() != null && !entidade.getTurma().getId().equals("")){			
			for (Turma turma : turmas) {
				if (turma.getId()==entidade.getTurma().getId())
					turnos = new TurmaDAO().buscarTurnosPorNomeDe(turma);				
			}			
		}			
		else
			turnos = new ArrayList<String>();
	}

	public List<String> getTurnos() {
		return turnos;
	}

}
