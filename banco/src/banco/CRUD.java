package banco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 *
 * @author juniorleao
 */
public class CRUD {

    /* 
    *o método lerArquivo lê o arquivo e retorna um vetor de 
    *strings onde cada linha tem uma linha do arquivo
     */
    public String[] lerArquivo(String nome) {
        File arquivo = new File(nome);
        String linha = new String();
        String[] vetorLinhas = new String[quantidadeLinhas(nome)];
        int i = 0;
        try {
            FileReader reader = new FileReader(arquivo);
            BufferedReader input = new BufferedReader(reader);
            while ((linha = input.readLine()) != null) {
                if (linha.length() > 27) {
                    throw new IllegalArgumentException("A linha tem mais que 27 caracteres");
                } else {
                    vetorLinhas[i++] = linha;
                }
            }
            input.close();
        } catch (IOException erro) {
            System.out.println(erro);
        }
        return vetorLinhas;
    }

    /*o método retorna quantas linhas tem o arquivo*/
    int quantidadeLinhas(String nome) {
        int quantidade = 0;
        try {
            File arquivo = new File(nome);
            LineNumberReader linha = new LineNumberReader(new FileReader(arquivo));
            linha.skip(arquivo.length());
            quantidade = linha.getLineNumber();
        } catch (IOException erro) {
            System.out.println(erro);
        }
        return quantidade;
    }

    /*
    *escreve no arquivo saida.txt o resultado final
     */
    public void escreverArquivo(String[] saida) {
        File arquivo = new File("saida.txt");
        try {
            FileWriter aux = new FileWriter(arquivo);
            for (int i = 0; i < saida.length; i++) {
                String linha = saida[i];
                aux.write(linha + System.getProperty("line.separator"));
                aux.flush();
            }
        } catch (IOException erro) {
            System.out.println(erro);
        }
    }

}
