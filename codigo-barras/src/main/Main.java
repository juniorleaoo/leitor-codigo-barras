package main;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		String nomeArquivo = "entrada.txt";

		Arquivo objetoArquivo = new Arquivo(nomeArquivo);
		Util objetoDados = new Util();

		System.out.println("O arquivo de entrada é: entrada.txt");
		System.out.println("O arquivo de saída é: saida.txt");

		resultadoFinal(objetoArquivo,objetoDados);
	}

	public static void resultadoFinal(Arquivo arquivo, Util dados) throws Exception {
		List<String> saida = dados.numerosEntrada(arquivo.lerArquivo(), arquivo.getQuantidadeLinhas());
		arquivo.escreverArquivo(saida);
	}
}
