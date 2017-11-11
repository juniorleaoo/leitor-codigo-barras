package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

	public static final int MAXIMO_CARACTERES = 27;

	public String nome;
	public int quantidadeLinhas;
	public File arquivo;

	public Arquivo(String nome) {
		this.arquivo = new File(nome);
		this.nome = nome;
	}

	public void setQuantidadeLinhas(int quantidadeLinhas) {
		this.quantidadeLinhas = quantidadeLinhas;
	}
	public int getQuantidadeLinhas() throws Exception {
		@SuppressWarnings("resource") LineNumberReader linha = new LineNumberReader(new FileReader(arquivo));
		linha.skip(arquivo.length());
		setQuantidadeLinhas(linha.getLineNumber());
		
		return quantidadeLinhas;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}

	public List<String> lerArquivo() throws Exception {
		File arquivo = new File(nome);
		String linha = new String();
		List<String> vetorLinhas = new ArrayList<>();

		FileReader reader = new FileReader(arquivo);
		@SuppressWarnings("resource") BufferedReader input = new BufferedReader(reader);

		while ((linha = input.readLine()) != null) {
			if (linha.length() > MAXIMO_CARACTERES) {
				throw new Exception("A linha tem mais que 27 caracteres");

			} else {
				vetorLinhas.add(linha);

			}
		}

		input.close();

		return vetorLinhas;
	}

	public void escreverArquivo(List<String> saida) throws Exception {

		File arquivo = new File("saida.txt");
		FileWriter escreverArquivo = new FileWriter(arquivo);

		for (String linha : saida) {
			escreverArquivo.write(linha + System.getProperty("line.separator"));
			escreverArquivo.flush();
		}

		escreverArquivo.close();

	}
}
