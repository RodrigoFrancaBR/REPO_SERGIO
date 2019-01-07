package br.com.franca.invicto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.franca.invicto.dao.AlunoDAO;
import br.com.franca.invicto.dao.ContratoDAO;
import br.com.franca.invicto.dao.ParcelaDAO;
import br.com.franca.invicto.model.Aluno;
import br.com.franca.invicto.model.CondicaoDoContrato;
import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Parcela;

@ManagedBean
@SessionScoped
public class ContratoBean extends CrudBean<Contrato, ContratoDAO> {

	private ContratoDAO contratoDao;

	private Aluno aluno = new Aluno();
/*	private String alunoNome;
	private String alunoCpf;	*/

	/*@PostConstruct
	public void init() {
		aluno = (Aluno) FacesContext.getCurrentInstance().getExternalContext()
				.getFlash().get("aluno");
	}*/

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

/*	@Override
	public void salvar() {
		//super.salvar();		
		new ContratoDAO().salvar(entidade,aluno);	
		CondicaoDoContrato condicao = entidade.getCondicaoDoContrato(entidade);
		gerarParcelasDoContrato(condicao, entidade);
	//	new AlunoDAO().salvar(aluno);
		// new AlunoBean().salvar();
	}*/
	

	/*public void consultar() {
		aluno = new AlunoDAO().buscarPorCpf(this.alunoCpf);
		alunoNome = aluno.getNome();
		alunoCpf = aluno.getCpf();		
		novo();
		entidade.setAluno(aluno);
	}*/

	public void consultar() {
		entidade.setAluno(new AlunoDAO().buscarPorCpf(entidade.getAluno().getCpf()));
		/*alunoNome = aluno.getNome();
		alunoCpf = aluno.getCpf();	*/	
		//novo();
		//entidade.setAluno(aluno);
	}
	
	/*public void gerarParcelasDoContrato(CondicaoDoContrato condicao, Contrato contrato){
		Parcela parcela = condicao.calculaParcela(contrato);
		new ParcelaDAO().salvar(parcela);
	}
*/
/*	
	public String getAlunoNome() {
		return alunoNome;
	}

	public void setAlunoNome(String alunoNome) {
		this.alunoNome = alunoNome;
	}

	public String getAlunoCpf() {
		return alunoCpf;
	}

	public void setAlunoCpf(String alunoCpf) {
		this.alunoCpf = alunoCpf;
	}
*/
}
