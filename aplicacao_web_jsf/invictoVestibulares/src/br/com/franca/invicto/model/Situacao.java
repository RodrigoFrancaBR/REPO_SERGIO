package br.com.franca.invicto.model;

public enum Situacao {
	//  Turma, Unidade 
	ATIVA(1, "Ativa"), DESATIVADA(0, "Desativada"),
	// Aluno, Contrato  
	ATIVO(1, "Ativo"), DESATIVADO(0, "Desativado"),

	// Matricula 
	MATRICULADO(2, "Matriculado"),
	CANCELADA(3, "Cancelada"),
	EM_ACORDO(4,"Em acordo"),
	ENCERRADA(5,"Encerrada");

	private final int numero;
	private final String nome;

	private Situacao(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}
	
	public int getNumero() {
		return this.numero;
	}
}
