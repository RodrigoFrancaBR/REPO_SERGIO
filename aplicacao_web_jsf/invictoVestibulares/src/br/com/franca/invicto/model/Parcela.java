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
	private Contrato contrato = new Contrato();
	private Integer numeroDaParcelaCurso;
	private Integer numeroDaParcelaMaterial;
	private Calendar dataVencimento = Calendar.getInstance();
	private BigDecimal valorPago;
	private Calendar dataPagamento = Calendar.getInstance();
	private BigDecimal valorDaParcelaDoCurso;
	private BigDecimal valorDaParcelaDoMaterial;
	private BigDecimal valorResidualDaParcelaCurso;
	private BigDecimal valorResidualDaParcelaMaterial;	
	//private BigDecimal descontoParcela;
	private BigDecimal taxaMatricula;
	private BigDecimal valorTotalDaParcela;
	private String situacaoDaParcela;
	private Integer numeroDaParcela;
	private List <Parcela> parcelas = new ArrayList<Parcela>();
	

	/*public BigDecimal calculaValorTotalDaParcela(BigDecimal taxaMatricula,
			BigDecimal valorDaParcelaDoCurso,
			BigDecimal valorDaParcelaDoMaterial,
			BigDecimal valorResidualDaParcelaCurso,
			BigDecimal valorResidualDaParcelaMaterial) {
		return this.valorTotalDaParcela.add(taxaMatricula)
				.add(valorDaParcelaDoCurso).add(valorDaParcelaDoMaterial)
				.add(valorResidualDaParcelaCurso)
				.add(valorResidualDaParcelaMaterial);
	}

	public BigDecimal calcularDescontoNaParcela(BigDecimal valorCurso,
			Double descontoCurso) {
		return descontoParcela = valorCurso.multiply(BigDecimal.valueOf(descontoCurso));		
	}
*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
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

	public BigDecimal getValorTotalDaParcela() {
		return valorTotalDaParcela;
	}

	public String getSituacaoDaParcela() {
		return situacaoDaParcela;
	}

	public void setSituacaoDaParcela(String situacaoDaParcela) {
		this.situacaoDaParcela = situacaoDaParcela;
	}

	public Integer getNumeroDaParcela() {
		return numeroDaParcela;
	}

	public void setNumeroDaParcela(Integer numeroDaParcela) {
		this.numeroDaParcela = numeroDaParcela;
	}

	public BigDecimal getValorResidualDaParcelaCurso() {
		return valorResidualDaParcelaCurso;
	}

	public void setValorResidualDaParcelaCurso(
			BigDecimal valorResidualDaParcelaCurso) {
		this.valorResidualDaParcelaCurso = valorResidualDaParcelaCurso;
	}

	public BigDecimal getValorResidualDaParcelaMaterial() {
		return valorResidualDaParcelaMaterial;
	}

	public void setValorResidualDaParcelaMaterial(
			BigDecimal valorResidualDaParcelaMaterial) {
		this.valorResidualDaParcelaMaterial = valorResidualDaParcelaMaterial;
	}

/*	public BigDecimal getDescontoParcela() {
		return descontoParcela;
	}

	public void setDescontoParcela(BigDecimal descontoParcela) {
		this.descontoParcela = descontoParcela;
	}*/

	public void setValorTotalDaParcela(BigDecimal valorTotalDaParcela) {
		this.valorTotalDaParcela = valorTotalDaParcela;
	}

	public List<Parcela> getParcelas() {
		return Collections.unmodifiableList(parcelas);
	}
	
/*	public void gerarParcela(Parcela parcela){
		parcela.getContrato().getCondicaoDoContrato().calculaParcelas();
	}*/

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public BigDecimal getTaxaMatricula() {
		return taxaMatricula;
	}

	public void setTaxaMatricula(BigDecimal taxaMatricula) {
		this.taxaMatricula = taxaMatricula;
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
	
	
	
	
}
