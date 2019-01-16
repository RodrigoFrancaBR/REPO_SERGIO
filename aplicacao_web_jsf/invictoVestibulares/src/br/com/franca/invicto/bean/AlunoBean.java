package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.HashMap;
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
	// private List<Turma> turmas;
	private Map<Integer, String> turmas = new HashMap<>();
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

	public Map<Integer, String> getTurmas() {
		String turma;
		// turmas do banco
		List<Turma> turmas = new TurmaDAO().buscar();

		for (Turma t : turmas) {
			// verificar se tem alguma turma no map
			// Se estiver vazia, coloca a primeira turma recuperada do banco no
			// map (nome e objeto turma)
			// e passa pra próxima turma do banco (continue)
			// Vai exisitir uma turma no banco logo vai pro else
			// Procura no map o nome da próxima turma e guarda na turma local
			// testa se o nome da turma do banco (t) é igual ao nome da turma
			// local (turma)
			if (this.turmas.isEmpty()) {
				this.turmas.put(t.getId(), t.getNome());
				continue;
			} else {
				turma  = this.turmas.get(t.getId());
				//turma = this.turmas.get(t.getId());
				if (null != turma) {
					if (t.getNome().equals(turma)) {
						System.out.println("Essa turma já está no map");
						continue;
					} else {
						this.turmas.put(t.getId(), t.getNome());
						continue;
					}
				} else {
					this.turmas.put(t.getId(), t.getNome());
					continue;
				}
			}
		}
		return this.turmas;
	}

	/*
	 * public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }
	 */

	public void onTurmaChange() {
		System.out.println("Passei por aqui");
		if (null != entidade.getTurma() && !entidade.getTurma().equals("")) {
			turnos = new TurmaDAO().buscarTurnosPorNomeDe(entidade.getTurma());
		} else
			turnos = new ArrayList<String>();
	}

	public List<String> getTurnos() {
		return turnos;
	}

}
