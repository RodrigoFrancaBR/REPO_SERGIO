package br.com.franca.invicto.model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Lancamento {
	private Integer id;
	private Calendar dataEmissao;
	private Despesa despesa = new Despesa();
	private Calendar dataVencimento;
	private BigDecimal valorPago;
	private Calendar dataPagamento;
	private String situacaoLancamento;
	private Calendar dataInicio = Calendar.getInstance();
	private Calendar dataFim = Calendar.getInstance();

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
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

	public String getSituacaoLancamento() {
		return situacaoLancamento;
	}

	public void setSituacaoLancamento(String situacaoLancamento) {
		this.situacaoLancamento = situacaoLancamento;
	}	

	public Calendar getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
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
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", dataEmissao=" + dataEmissao + ", despesa=" + despesa + ", dataVencimento="
				+ dataVencimento + ", valorPago=" + valorPago + ", dataPagamento=" + dataPagamento
				+ ", situacaoLancamento=" + situacaoLancamento + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ "]";
	}

	

}
