package br.com.franca.invicto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private BigDecimal taxaMatricula = new BigDecimal(0);
	private BigDecimal valorCurso;
	private Double descontoCurso;
	private Integer qtdParcelasCurso;
	private BigDecimal residualDaParcelaDoCurso;
	private BigDecimal valorMaterial;
	private Integer qtdParcelasMaterial;
	private BigDecimal residualDaParcelaDoMaterial;
	private Integer diaVencimento;
	private String formaDePagamento;
	private Calendar dataMatricula = Calendar.getInstance();
	private Situacao situacao;
	private String matricula;

	private Aluno aluno = new Aluno();
	private Turma turma = new Turma();
	private CondicaoDoContrato condicaoDoContrato;
	private CondicaoContrato condicaoContratoEnum;
	private List<Parcela> parcelas = new ArrayList<Parcela>();

	public Contrato() {
	}

	public void setCondicaoDoContrato(Integer qtdParcelasCurso, Integer qtdParcelasMaterial) {
		if (qtdParcelasCurso == 1 && qtdParcelasMaterial == 1) {
			this.condicaoDoContrato = new CursoMaterialAvista();
			this.condicaoContratoEnum = CondicaoContrato.CURSO_MATERIAL_AVISTA;
		} else if (qtdParcelasCurso == 1 && qtdParcelasMaterial >= 2) {
			this.condicaoDoContrato = new CursoAvistaMaterialParcelado();
			this.condicaoContratoEnum = CondicaoContrato.CURSO_AVISTA_MATERIAL_PARCELADO;
		} else {
			if (qtdParcelasCurso >= 2 && qtdParcelasMaterial == 1) {
				this.condicaoDoContrato = new CursoParceladoMaterialAvista();
				this.condicaoContratoEnum = CondicaoContrato.CURSO_PARCELADO_MATERIAL_AVISTA;
			} else {
				if (qtdParcelasCurso >= 2 && qtdParcelasMaterial >= 2) {
					this.condicaoContratoEnum = CondicaoContrato.CURSO_MATERIAL_PARCELADO;
					this.condicaoDoContrato = new CursoMaterialParcelado();
				}
			}

		}
	}
	
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

	public Integer getQtdParcelasCurso() {
		return qtdParcelasCurso;
	}

	public void setQtdParcelasCurso(Integer qtdParcelasCurso) {
		this.qtdParcelasCurso = qtdParcelasCurso;
	}

	public BigDecimal getResidualDaParcelaDoCurso() {
		return residualDaParcelaDoCurso;
	}

	public void setResidualDaParcelaDoCurso(BigDecimal residualDaParcelaDoCurso) {
		this.residualDaParcelaDoCurso = residualDaParcelaDoCurso;
	}

	public BigDecimal getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(BigDecimal valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public Integer getQtdParcelasMaterial() {
		return qtdParcelasMaterial;
	}

	public void setQtdParcelasMaterial(Integer qtdParcelasMaterial) {
		this.qtdParcelasMaterial = qtdParcelasMaterial;
	}

	public BigDecimal getResidualDaParcelaDoMaterial() {
		return residualDaParcelaDoMaterial;
	}

	public void setResidualDaParcelaDoMaterial(BigDecimal residualDaParcelaDoMaterial) {
		this.residualDaParcelaDoMaterial = residualDaParcelaDoMaterial;
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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public CondicaoDoContrato getCondicaoDoContrato() {
		return condicaoDoContrato;
	}

	public void setCondicaoDoContrato(CondicaoDoContrato condicaoDoContrato) {
		this.condicaoDoContrato = condicaoDoContrato;
	}

	public CondicaoContrato getCondicaoContratoEnum() {
		return condicaoContratoEnum;
	}

	public void setCondicaoContratoEnum(CondicaoContrato condicaoContratoEnum) {
		this.condicaoContratoEnum = condicaoContratoEnum;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
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

	@Override
	public String toString() {
		return "Contrato [id=" + id + ", taxaMatricula=" + taxaMatricula + ", valorCurso=" + valorCurso
				+ ", descontoCurso=" + descontoCurso + ", qtdParcelasCurso=" + qtdParcelasCurso
				+ ", residualDaParcelaDoCurso=" + residualDaParcelaDoCurso + ", valorMaterial=" + valorMaterial
				+ ", qtdParcelasMaterial=" + qtdParcelasMaterial + ", residualDaParcelaDoMaterial="
				+ residualDaParcelaDoMaterial + ", diaVencimento=" + diaVencimento + ", formaDePagamento="
				+ formaDePagamento + ", dataMatricula=" + dataMatricula + ", situacao=" + situacao + ", matricula="
				+ matricula + ", aluno=" + aluno + ", turma=" + turma + ", condicaoDoContrato=" + condicaoDoContrato
				+ ", parcelas=" + parcelas + "]";
	}

}
