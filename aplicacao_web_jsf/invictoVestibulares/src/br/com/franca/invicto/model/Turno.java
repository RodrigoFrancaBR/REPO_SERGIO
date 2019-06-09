package br.com.franca.invicto.model;

public enum Turno {
	// Declaração das constantes
	M(1, "Manhã"), T(2, "Tarde"), N(3, "Noite");

	// Definição das constantes
	private final int numero;
	private final String nome;

	// métodos para acessar os valores
	public int numero() {
		return this.numero;
	}

	public String nome() {
		return this.nome;
	}

	//método que define as constantes
	private Turno(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}
	
	

}
