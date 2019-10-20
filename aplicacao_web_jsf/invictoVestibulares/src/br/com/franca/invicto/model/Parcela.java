package br.com.franca.invicto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Parcela implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	// private Contrato contrato = new Contrato();
	private Integer numeroDaParcelaCurso;
	private Integer numeroDaParcelaMaterial;
	private Calendar dataVencimento = Calendar.getInstance();
	private BigDecimal valorPago;
	private Calendar dataPagamento = Calendar.getInstance();
	private BigDecimal valorDaParcelaDoCurso;
	private BigDecimal valorDaParcelaDoMaterial;
	private BigDecimal valorResidualDaParcelaCurso;
	private BigDecimal valorResidualDaParcelaMaterial;
	private BigDecimal taxaMatricula;
	private BigDecimal valorTotalDaParcela;
	private Situacao situacao;
	private Integer numeroDaParcela;
	// private List<Parcela> parcelas = new ArrayList<Parcela>();

	public Parcela() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroDaParcelaCurso() {
		return numeroDaParcelaCurso;
	}

	public void setNumeroDaParcelaCurso(Integer numeroDaParcelaCurso) {
		this.numeroDaParcelaCurso = numeroDaParcelaCurso;
	}

	public Integer getNumeroDaParcelaMaterial() {
		return numeroDaParcelaMaterial;
	}

	public void setNumeroDaParcelaMaterial(Integer numeroDaParcelaMaterial) {
		this.numeroDaParcelaMaterial = numeroDaParcelaMaterial;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorDaParcelaDoCurso() {
		return valorDaParcelaDoCurso;
	}

	public void setValorDaParcelaDoCurso(BigDecimal valorDaParcelaDoCurso) {
		this.valorDaParcelaDoCurso = valorDaParcelaDoCurso;
	}

	public BigDecimal getValorDaParcelaDoMaterial() {
		return valorDaParcelaDoMaterial;
	}

	public void setValorDaParcelaDoMaterial(BigDecimal valorDaParcelaDoMaterial) {
		this.valorDaParcelaDoMaterial = valorDaParcelaDoMaterial;
	}

	public BigDecimal getValorResidualDaParcelaCurso() {
		return valorResidualDaParcelaCurso;
	}

	public void setValorResidualDaParcelaCurso(BigDecimal valorResidualDaParcelaCurso) {
		this.valorResidualDaParcelaCurso = valorResidualDaParcelaCurso;
	}

	public BigDecimal getValorResidualDaParcelaMaterial() {
		return valorResidualDaParcelaMaterial;
	}

	public void setValorResidualDaParcelaMaterial(BigDecimal valorResidualDaParcelaMaterial) {
		this.valorResidualDaParcelaMaterial = valorResidualDaParcelaMaterial;
	}

	public BigDecimal getTaxaMatricula() {
		return taxaMatricula;
	}

	public void setTaxaMatricula(BigDecimal taxaMatricula) {
		this.taxaMatricula = taxaMatricula;
	}

	public BigDecimal getValorTotalDaParcela() {
		return valorTotalDaParcela;
	}

	public void setValorTotalDaParcela(BigDecimal valorTotalDaParcela) {
		this.valorTotalDaParcela = valorTotalDaParcela;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Integer getNumeroDaParcela() {
		return numeroDaParcela;
	}

	public void setNumeroDaParcela(Integer numeroDaParcela) {
		this.numeroDaParcela = numeroDaParcela;
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
		Parcela other = (Parcela) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parcela [id=" + id + ", numeroDaParcelaCurso=" + numeroDaParcelaCurso + ", numeroDaParcelaMaterial="
				+ numeroDaParcelaMaterial + ", dataVencimento=" + dataVencimento + ", valorPago=" + valorPago
				+ ", dataPagamento=" + dataPagamento + ", valorDaParcelaDoCurso=" + valorDaParcelaDoCurso
				+ ", valorDaParcelaDoMaterial=" + valorDaParcelaDoMaterial + ", valorResidualDaParcelaCurso="
				+ valorResidualDaParcelaCurso + ", valorResidualDaParcelaMaterial=" + valorResidualDaParcelaMaterial
				+ ", taxaMatricula=" + taxaMatricula + ", valorTotalDaParcela=" + valorTotalDaParcela + ", situacao="
				+ situacao + ", numeroDaParcela=" + numeroDaParcela + "]";
	}
	
	

}
