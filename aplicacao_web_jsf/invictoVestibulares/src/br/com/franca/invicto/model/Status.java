package br.com.franca.invicto.model;

public enum Status {
	A(1, "Ativo"), I(0, "Inativo");
	
	private final int numero;
	private final String nome;

	private Status(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}
}
