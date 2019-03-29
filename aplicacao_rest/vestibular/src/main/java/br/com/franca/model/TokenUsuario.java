package br.com.franca.model;

public class TokenUsuario {
	private Long id;
	private String tipo;
	private String nome;
	private String senha;
	private String matricula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
		TokenUsuario other = (TokenUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TokenUsuario [id=" + id + ", tipo=" + tipo + ", nome=" + nome + ", senha=" + senha + ", matricula="
				+ matricula + "]";
	}
	
	
	public static boolean verificarDVMatricula(String matriculaComDV) {
		boolean matriculaValida = false;
		if (matriculaComDV.length() >= 2 && matriculaComDV.length() <= 8) {
			try {
				System.out.println(matriculaComDV.length());
				System.out.println(matriculaComDV.length() - 1);
				int matriculaSemDV = Integer.parseInt(matriculaComDV.substring(0, matriculaComDV.length() - 1));
				if (matriculaSemDV == 0) {
					throw new NumberFormatException();
				}
				int dv = Integer.parseInt(matriculaComDV.substring(matriculaComDV.length() - 1));
				int dvRetorno = calcularDvMatricula(matriculaSemDV);
				matriculaValida = (dv == dvRetorno);
			} catch (NumberFormatException e) {
			}
		}
		return matriculaValida;
	}
	

	public static int calcularDvMatricula(int matriculaSemDV) {

		int soma = 0;
		int[] sequenciaFixaCalculoDV = new int[] { 3, 4, 5, 6, 7, 8, 9, 3, 4, 5, 6, 7, 8, 9 };
		String matriculaInvertida = new StringBuilder(String.valueOf(matriculaSemDV)).reverse().toString();

		// Multiplico cada unidade da matricula já invertida pela sequencia
		// fixa e acumulo o resultado da multiplicação
		for (int i = 0; i < matriculaInvertida .length(); i++) {
			soma += sequenciaFixaCalculoDV[i] * Integer.parseInt(matriculaInvertida.substring(i, i + 1));
		}

		// Pego o resto da divisão por 11 e com o resultado subitraio 11,
		// exceto quando o resultado for 0 ou 1
		int digito = soma % 11;
		if (digito > 1) {
			digito = 11 - digito;
		}

		return digito;
	}

	
}
