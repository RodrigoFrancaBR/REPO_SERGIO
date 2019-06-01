package br.com.franca.model;

public class Unidade {

	private String nome;
	private String turma;
	
	public String getNome() {
		System.out.println(nome);
		return nome;
	}
	
	/*public void setNome(String nome) {
		
		if(nome.contains("^[a-Z]")){
			System.out.println("Tem letras");
			this.nome = nome;
			System.out.println(nome);
		}
		else {
			System.out.println("Apenas numeros");
		}
		
	}*/
	
	public void setNome(String nome){
		System.out.println(nome);
		this.nome = nome;
	}
	
	public String getTurma() {
		return turma;
	}
	
	public void setTurma(String turma) {
		this.turma = turma;
	}
	
}
