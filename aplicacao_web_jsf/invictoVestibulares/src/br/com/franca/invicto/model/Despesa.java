package br.com.franca.invicto.model;

import java.math.BigDecimal;

public class Despesa {
	private Integer id;
	private Categoria categoria = new Categoria();
	private Funcionario funcionario = new Funcionario();
	private BigDecimal valorDespesa;
	private Integer diaVencimento;
	private String viaRecebido;
	private Boolean ativo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public BigDecimal getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getViaRecebido() {
		return viaRecebido;
	}

	public void setViaRecebido(String viaRecebido) {
		this.viaRecebido = viaRecebido;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Despesa [id=" + id + ", categoria=" + categoria + ", funcionario=" + funcionario + ", valorDespesa="
				+ valorDespesa + ", diaVencimento=" + diaVencimento + ", viaRecebido=" + viaRecebido + ", ativo="
				+ ativo + "]";
	}
	
	

}
