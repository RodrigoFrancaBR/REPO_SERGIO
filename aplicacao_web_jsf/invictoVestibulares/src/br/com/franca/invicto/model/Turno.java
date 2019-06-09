package br.com.franca.invicto.model;

public enum Turno {
	// Declara��o das constantes
	M(1, "Manh�"), T(2, "Tarde"), N(3, "Noite");

	// Defini��o das constantes
	private final int numero;
	private final String nome;

	// m�todos para acessar os valores
	public int numero() {
		return this.numero;
	}

	public String nome() {
		return this.nome;
	}

	//m�todo que define as constantes
	private Turno(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}
	
	

}
