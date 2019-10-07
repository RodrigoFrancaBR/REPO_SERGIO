package br.com.franca.invicto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Contrato implements Serializable {
	protected static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal taxaMatricula;
	private BigDecimal valorCurso;
	private Double descontoCurso;
	private Integer qtdParcelasCurso;
	private BigDecimal valorMaterial;
	private Integer qtdParcelasMaterial;
	private Integer diaVencimento;
	private String formaDePagamento;
	private Calendar dataMatricula = Calendar.getInstance();
	private String cursoAvista;
	private String materialAvista;
	private Situacao situacao;
	private String matricula;
	
	private Aluno aluno = new Aluno();
	private Turma turma = new Turma();
	private CondicaoDoContrato condicaoDoContrato;
	private List<Parcela> parcelas = new ArrayList<Parcela>();

	
	private List<Integer> qtdParcelas = new ArrayList<Integer>() {
		{
			for (int i = 2; i <= 15; i++) {
				add((i));
			}
		}
	};


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getTaxaMatricula() {
		return taxaMatricula;
	}

	public void setTaxaMatricula(BigDecimal taxaMatricula) {
		this.taxaMatricula = taxaMatricula;
	}

	public BigDecimal getValorCurso() {
		return valorCurso;
	}

	public void setValorCurso(BigDecimal valorCurso) {
		this.valorCurso = valorCurso;
	}

	public Double getDescontoCurso() {
		return descontoCurso;
	}

	public void setDescontoCurso(Double descontoCurso) {
		this.descontoCurso = descontoCurso;
	}

	public BigDecimal getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(BigDecimal valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Calendar getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Calendar dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getCursoAvista() {
		return cursoAvista;
	}

	public void setCursoAvista(String cursoAvista) {
		this.cursoAvista = cursoAvista;
	}

	public String getMaterialAvista() {
		return materialAvista;
	}

	public void setMaterialAvista(String materialAvista) {
		this.materialAvista = materialAvista;
	}	

	public List<Integer> getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(List<Integer> qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getQtdParcelasCurso() {
		return qtdParcelasCurso;
	}

	public void setQtdParcelasCurso(Integer qtdParcelasCurso) {
		this.qtdParcelasCurso = qtdParcelasCurso;
	}

	public Integer getQtdParcelasMaterial() {
		return qtdParcelasMaterial;
	}

	public void setQtdParcelasMaterial(Integer qtdParcelasMaterial) {
		this.qtdParcelasMaterial = qtdParcelasMaterial;
	}

	/*
	 * public void setCondicaoDoContrato(Contrato contrato) { if
	 * (contrato.getQtdParcelasCurso() == 1 && contrato.getQtdParcelasMaterial() ==
	 * 1) contrato.condicaoDoContrato = new CursoMaterialAvista(); else { if
	 * (contrato.getQtdParcelasCurso() == 1 && contrato.getQtdParcelasMaterial() >=
	 * 2) { contrato.condicaoDoContrato = new CursoAvistaMaterialParcelado(); } else
	 * { if (contrato.getQtdParcelasCurso() >= 2 &&
	 * contrato.getQtdParcelasMaterial() == 1) { contrato.condicaoDoContrato = new
	 * MaterialAvistaCursoParcelado(); } else { if (contrato.getQtdParcelasCurso()
	 * >= 2 && contrato.getQtdParcelasMaterial() >= 2) { contrato.condicaoDoContrato
	 * = new CursoMaterialParcelado(); } }
	 * 
	 * } } }
	 */

	public CondicaoDoContrato getCondicaoDoContrato() {
		return condicaoDoContrato;
	}
	
	public void setCondicaoDoContrato(Integer qtdParcelasCurso, Integer qtdParcelasMaterial) {
		if (qtdParcelasCurso == 1 && qtdParcelasMaterial == 1)
			this.condicaoDoContrato = new CursoMaterialAvista();
		else {
			if (qtdParcelasCurso == 1 && qtdParcelasMaterial >= 2) {
				this.condicaoDoContrato = new CursoAvistaMaterialParcelado();
			} else {
				if (qtdParcelasCurso >= 2 && qtdParcelasMaterial == 1) {
					this.condicaoDoContrato = new CursoParceladoMaterialAvista();
				} else {
					if (qtdParcelasCurso >= 2 && qtdParcelasMaterial >= 2) {
						this.condicaoDoContrato = new CursoMaterialParcelado();
					}
				}

			}
		}
	}


	/*
	 * public void setCondicaoDoContrato(CondicaoDoContrato condicaoDoContrato) {
	 * this.condicaoDoContrato = condicaoDoContrato; }
	 */

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	
}
