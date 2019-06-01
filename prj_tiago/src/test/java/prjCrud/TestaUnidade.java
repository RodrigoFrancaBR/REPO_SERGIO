package prjCrud;

import java.util.Scanner;

import br.com.franca.model.Unidade;

public class TestaUnidade {

	public static void main(String[] args) {
		
		Unidade unidade = new Unidade();
		unidade.setNome("Cascadura");
		String nome = unidade.getNome();
		System.out.println(nome);
		
		Scanner sc = new Scanner(System.in);
		String s =  sc.nextLine();
		System.out.println(s);
		
		
		
	}

}
