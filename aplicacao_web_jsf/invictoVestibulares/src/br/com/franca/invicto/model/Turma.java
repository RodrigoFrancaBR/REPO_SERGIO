package br.com.franca.invicto.model;

import java.io.Serializable;

public class Turma implements  Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Boolean ativo;
	private Unidade unidade = new Unidade();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@Override
	public String toString() {
		return "Turma [id=" + id + ", nome=" + nome + ", ativo=" + ativo + ", unidade=" + unidade + "]";
	}


	

}
