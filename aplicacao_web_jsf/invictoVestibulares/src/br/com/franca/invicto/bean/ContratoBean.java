package br.com.franca.invicto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.franca.invicto.dao.AlunoDAO;
import br.com.franca.invicto.dao.ContratoDAO;
import br.com.franca.invicto.dao.ParcelaDAO;
import br.com.franca.invicto.dao.TurmaDAO;
import br.com.franca.invicto.model.Aluno;
import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Parcela;
import br.com.franca.invicto.model.Turma;

@ManagedBean
@SessionScoped
public class ContratoBean extends CrudBean<Contrato, ContratoDAO> implements Serializable {

	private static final long serialVersionUID = 8829084366036898197L;

	private ContratoDAO contratoDAO;
	private ParcelaDAO parcelaDAO;
	private TurmaDAO turmaDAO;
	private AlunoDAO alunoDAO;

	private List<Integer> dias;
	private List<Integer> parcelas;
	private List<Turma> turmas;

	public ContratoBean() {
		if (alunoDAO == null) {
			alunoDAO = new AlunoDAO();
		}

		if (parcelaDAO == null) {
			parcelaDAO = new ParcelaDAO();
		}
	}

	public List<Integer> getParcelas() {
		if (null == parcelas) {
			parcelas = new ArrayList<Integer>();
			for (int i = 2; i <= 15; i++) {
				parcelas.add((i));
			}
		}
		return parcelas;
	};

	public List<Integer> getDias() {
		if (null == dias) {
			dias = new ArrayList<Integer>();
			for (int i = 1; i <= 31; i++) {
				dias.add(i);
			}
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

	public void salvarContrato() {
		salvar();
		/*
		 * if (null == parcelaDAO) { parcelaDAO = new ParcelaDAO(); }
		 */
		parcelaDAO.salvarParcelas(entidade);
	}

	@Override
	public Contrato criarNovaEntidade() {
		return new Contrato();
	}

	public List<SelectItem> getTurmas() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		List<Turma> turmas = new TurmaDAO().buscar();
		for (Turma turma : turmas) {
			// observem que o value do meu SelectItem é a própria entidade
			itens.add(new SelectItem(turma, turma.getNome()));
		}
		return itens;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public void simularContrato() {
		Aluno alunoEncontrado = this.alunoDAO.buscarPorCpf(this.entidade.getAluno().getCpf());
		if (null == alunoEncontrado) {
			throw new RuntimeException("Aluno n�o encontrado");
		} else {
			this.entidade.setAluno(alunoEncontrado);
		}

		this.entidade.setCondicaoDoContrato(this.entidade.getQtdParcelasCurso(),
				this.entidade.getQtdParcelasMaterial());
		List<Parcela> parcelas = this.entidade.getCondicaoDoContrato().calculaParcelas(this.entidade);
		this.entidade.setParcelas(parcelas);
		mudarParaSimular();
	}

	public void buscarParcelas(Contrato contrato) {
		entidade = parcelaDAO.buscarParcelasPorContrato(contrato);
		mudarParaParcelasDoContrato();
	}

	public void receberPagamento(Parcela parcela) {
		System.out.println(parcela);
	}

}
