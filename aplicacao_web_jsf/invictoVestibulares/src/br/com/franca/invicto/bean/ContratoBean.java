package br.com.franca.invicto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.AlunoDAO;
import br.com.franca.invicto.dao.ContratoDAO;
import br.com.franca.invicto.dao.TurmaDAO;
import br.com.franca.invicto.model.Aluno;
import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Parcela;
import br.com.franca.invicto.model.Turma;

@ManagedBean
@SessionScoped
public class ContratoBean extends CrudBean<Contrato, ContratoDAO> {

	private ContratoDAO contratoDAO;
	private TurmaDAO turmaDAO;
	private AlunoDAO alunoDAO;
	private Aluno aluno = new Aluno();
	private List<Integer> dias = new ArrayList<>();

	public ContratoBean() {
		if (null == this.turmaDAO) {
			this.turmaDAO = new TurmaDAO();
		}
		if (null == this.alunoDAO) {
			this.alunoDAO = new AlunoDAO();
		}
	}

	public List<Integer> getDias() {
		for (int i = 1; i <= 31; i++) {
			dias.add(i);
		}
		return dias;
	}

	@Override
	public ContratoDAO getDao() {
		if (contratoDAO == null) {
			contratoDAO = new ContratoDAO();
		}
		return contratoDAO;
	}

	@Override
	public Contrato criarNovaEntidade() {
		return new Contrato();
	}

	public void simularContrato() {
		System.out.println(this.entidade);
		this.entidade.setAluno(this.alunoDAO.buscarPorCpf(this.entidade.getAluno().getCpf()));
		this.entidade.setTurma(this.turmaDAO.buscarPorId(this.entidade.getTurma().getId()));
		this.entidade.setCondicaoDoContrato(this.entidade.getQtdParcelasCurso(),
				this.entidade.getQtdParcelasMaterial());
		List<Parcela> parcelas = this.entidade.getCondicaoDoContrato().calculaParcelas(this.entidade);
		this.entidade.setParcelas(parcelas);
		mudarParaSimular();
	}

	public List<Turma> getTurmas() {
		/*
		 * if (null == this.turmaDAO) { this.turmaDAO = new TurmaDAO(); }
		 */
		return this.turmaDAO.buscar();
	}

	/*
	 * public void consultar() { entidade.setAluno(new
	 * AlunoDAO().buscarPorCpf(entidade.getAluno().getCpf())); }
	 */

}
