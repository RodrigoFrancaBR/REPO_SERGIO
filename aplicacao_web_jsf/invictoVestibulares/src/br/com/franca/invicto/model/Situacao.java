package br.com.franca.invicto.model;

public enum Situacao {

	// Turma, Unidade
	DESATIVADA(0, "Desativada"),

	ATIVA(1, "Ativa"),

	// Aluno, Contrato
	ATIVO(2, "Ativo"),

	DESATIVADO(3, "Desativado"),

	// Matricula
	MATRICULADO(4, "Matriculado"),

	CANCELADA(5, "Cancelada"),

	EM_ACORDO(6, "Em acordo"),

	ENCERRADA(7, "Encerrada"),
	
	PAGO (8,"Pago"),
	
	A_VENCER (9,"A vencer");

	private final int codigo;
	private final String descricao;

	private Situacao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static Situacao valueOf(int codigo) {
		switch (codigo) {
		case 0:
			return DESATIVADA;
		case 1:
			return ATIVA;
		case 2:
			return ATIVO;
		case 3:
			return DESATIVADO;
		case 4:
			return MATRICULADO;
		case 5:
			return CANCELADA;
		case 6:
			return EM_ACORDO;
		case 7:
			return ENCERRADA;
		case 8:
			return PAGO;
		case 9:
			return A_VENCER;	
		default:
			break;
		}
		return null;
	}	

	public int getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

}

