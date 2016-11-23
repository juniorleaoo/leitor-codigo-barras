package banco;

import java.util.ArrayList;

/**
 *
 * @author juniorleao
 */
public class TransformarDados {

    /*retorna um vetor de string com todas as entradas do arquivo verificadas*/
    public String[] numerosEntrada(String[] entrada, int quantidadeLinhas) {
        /*
        *como sei que cara conta tem 4 linhas, divido por 4 para saber quantas contas vão ser geradas
        *e assim criar um vetor de tamanho fixo
        */
        String[] resultadoFinal = new String[((quantidadeLinhas + 1) / 4)];
        ArrayList<String> possiveisNumerosCorrigidos = new ArrayList<String>();
        String numeroContaSemCorrigir = new String();
        for (int j = 0, k = 0; j < quantidadeLinhas; j += 4) {
            numeroContaSemCorrigir = numeroConta(entrada[j], entrada[j + 1], entrada[j + 2]);

            if (verificarConta(numeroContaSemCorrigir)) {
                resultadoFinal[k++] = numeroContaSemCorrigir;
            } else if (numeroContaSemCorrigir.contains("?")) {
                possiveisNumerosCorrigidos = corrigirNumero(entrada[j], entrada[j + 1], entrada[j + 2]);
                int quantidadeNumerosCorrigidos = possiveisNumerosCorrigidos.size();
                if (quantidadeNumerosCorrigidos > 1) {
                    resultadoFinal[k] = numeroContaSemCorrigir + " AMB [";
                    for (int i = 0; i < quantidadeNumerosCorrigidos; i++) {
                        resultadoFinal[k] += possiveisNumerosCorrigidos.get(i) + " ";
                    }
                    resultadoFinal[k] += "]";
                    k++;
                } else if (quantidadeNumerosCorrigidos == 1) {
                    final int zero = 0;
                    if (possiveisNumerosCorrigidos.get(zero).contains("?")) {
                        resultadoFinal[k++] = possiveisNumerosCorrigidos.get(zero) + " ILL";
                    } else {
                        resultadoFinal[k++] = possiveisNumerosCorrigidos.get(zero);
                    }
                }
            } else {
                possiveisNumerosCorrigidos = corrigirNumero(entrada[j], entrada[j + 1], entrada[j + 2]);
                int quantidadeNumerosCorrigidos = possiveisNumerosCorrigidos.size();
                if (quantidadeNumerosCorrigidos > 1) {
                    resultadoFinal[k] = numeroContaSemCorrigir + " AMB [";
                    for (int i = 0; i < quantidadeNumerosCorrigidos; i++) {
                        resultadoFinal[k] += possiveisNumerosCorrigidos.get(i) + " ";
                    }
                    resultadoFinal[k] += "]";
                    k++;
                } else if (quantidadeNumerosCorrigidos == 1) {
                    final int zero = 0;
                    if (verificarConta(possiveisNumerosCorrigidos.get(zero))) {
                        resultadoFinal[k++] = possiveisNumerosCorrigidos.get(zero);
                    } else {
                        resultadoFinal[k++] = possiveisNumerosCorrigidos.get(zero) + " ERR";
                    }
                }
            }
        }
        return resultadoFinal;
    }

    /*retorna uma string que contem o número da conta*/
    public String numeroConta(String linha1, String linha2, String linha3) {
        String soma = new String();
        for (int i = 0; i < linha1.length(); i += 3) {
            soma += numero(linha1.substring(i, i + 3), linha2.substring(i, i + 3), linha3.substring(i, i + 3));
        }
        return soma;
    }

    /*verifica se a conta é válida*/
    public boolean verificarConta(String conta) {
        int soma = 0;
        int j = 0;
        if (conta.length() > 9) {
            throw new IllegalArgumentException("A conta tem mais que 9 digitos");
        }
        if (conta.contains("?")) {
            return false;
        } else {
            for (int i = 9; i > 0; i--) {
                soma += (i * Character.getNumericValue(conta.charAt(j++)));
            }
            return ((soma % 11) == 0);
        }
    }

    /*retorna o digito correspondente da entrada*/
    public String numero(String linha1, String linha2, String linha3) {
        if ((linha1.equals("   ")) && (linha2.equals("  |")) && (linha3.equals("  |"))) {
            return "1";
        } else if (linha1.equals(" _ ") && linha2.equals(" _|") && linha3.equals("|_ ")) {
            return "2";
        } else if (linha1.equals(" _ ") && linha2.equals(" _|") && linha3.equals(" _|")) {
            return "3";
        } else if (linha1.equals("   ") && linha2.equals("|_|") && linha3.equals("  |")) {
            return "4";
        } else if (linha1.equals(" _ ") && linha2.equals("|_ ") && linha3.equals(" _|")) {
            return "5";
        } else if (linha1.equals(" _ ") && linha2.equals("|_ ") && linha3.equals("|_|")) {
            return "6";
        } else if (linha1.equals(" _ ") && linha2.equals("  |") && linha3.equals("  |")) {
            return "7";
        } else if (linha1.equals(" _ ") && linha2.equals("|_|") && linha3.equals("|_|")) {
            return "8";
        } else if (linha1.equals(" _ ") && linha2.equals("|_|") && linha3.equals(" _|")) {
            return "9";
        } else if (linha1.equals(" _ ") && linha2.equals("| |") && linha3.equals("|_|")) {
            return "0";
        }
        return "?";
    }

    /*troca o caracter específico da linha*/
    public String trocaChar(char troca, String linha1, int i) {
        StringBuilder aux = new StringBuilder(linha1);
        aux.setCharAt(i, troca);
        return aux.toString();
    }

    /*
    *troca o caracter com a funcao trocaChar e retorna o novo numero da conta com o caracter trocado
    *da primeira linha da conta
    */
    public String possivelNumeroContaLinha1(char troca, String linha, String linha2, String linha3, int i) {
        String aux = trocaChar(troca, linha, i);
        return numeroConta(aux, linha2, linha3);
    }
    
    /*
    *troca o caracter com a funcao trocaChar e retorna o novo numero da conta com o caracter trocado
    *da segunda linha da conta
    */
    public String possivelNumeroContaLinha2(char troca, String linha, String linha2, String linha3, int i) {
        String aux = trocaChar(troca, linha, i);
        return numeroConta(linha2, aux, linha3);
    }
    
   /*
    *troca o caracter com a funcao trocaChar e retorna o novo numero da conta com o caracter trocado
    *da terceira linha da conta
    */
    public String possivelNumeroContaLinha3(char troca, String linha, String linha2, String linha3, int i) {
        String aux = trocaChar(troca, linha, i);
        return numeroConta(linha2, linha3, aux);
    }

    /*vai retornar todos os possiveis numeros da conta corrigido*/
    public ArrayList<String> corrigirNumero(String linha1, String linha2, String linha3) {
        /*
        *como não se sabe quantas possibilidades que existe para um determinado numero errado
        *o array vai ser de tamanho dinâmico e não estático
        *vai trocar cara possivel caracter da linha um e ver se gerou um numero e validar esse mesmo numero
        *se o numero for válido, vai ser um posivel numero
        */
        ArrayList<String> possiveis = new ArrayList<String>();
        for (int i = 0; i < linha1.length(); i++) {
            /*LINHA 1*/
            if (linha1.charAt(i) == ' ') {
                String num = possivelNumeroContaLinha1('_', linha1, linha2, linha3, i);
                if (verificarConta(num)) {
                    possiveis.add(num);
                }
            }
            if (linha1.charAt(i) == '_') {
                String num = possivelNumeroContaLinha1(' ', linha1, linha2, linha3, i);
                if (verificarConta(num)) {
                    possiveis.add(num);
                }
            }
            /*LINHA2*/
            if (linha2.charAt(i) == ' ') {
                String num = possivelNumeroContaLinha2('|', linha2, linha1, linha3, i);
                if (verificarConta(num)) {
                    possiveis.add(num);
                } else {
                    num = possivelNumeroContaLinha2('_', linha2, linha1, linha3, i);
                    if (verificarConta(num)) {
                        possiveis.add(num);
                    }
                }
            }
            if (linha2.charAt(i) == '|') {
                String num = possivelNumeroContaLinha2(' ', linha2, linha1, linha3, i);
                if (verificarConta(num)) {
                    possiveis.add(num);
                } else {
                    num = possivelNumeroContaLinha2(' ', linha2, linha1, linha3, i);
                    if (verificarConta(num)) {
                        possiveis.add(num);
                    }
                }
            }
            if (linha2.charAt(i) == '_') {
                String num = possivelNumeroContaLinha2(' ', linha2, linha1, linha3, i);
                if (verificarConta(num)) {
                    possiveis.add(num);
                } else {
                    num = possivelNumeroContaLinha2('|', linha2, linha1, linha3, i);
                    if (verificarConta(num)) {
                        possiveis.add(num);
                    }
                }
            }
            /*LINHA 3*/
            if (linha3.charAt(i) == ' ') {
                String num = possivelNumeroContaLinha3('|', linha3, linha1, linha2, i);
                if (verificarConta(num)) {
                    possiveis.add(num);
                } else {
                    num = possivelNumeroContaLinha3('_', linha3, linha1, linha2, i);
                    if (verificarConta(num)) {
                        possiveis.add(num);
                    }
                }
            }
            if (linha3.charAt(i) == '|') {
                String num = possivelNumeroContaLinha3(' ', linha3, linha1, linha2, i);
                if (verificarConta(num)) {
                    possiveis.add(num);
                } else {
                    num = possivelNumeroContaLinha3('_', linha3, linha1, linha2, i);
                    if (verificarConta(num)) {
                        possiveis.add(num);
                    }
                }
            }
            if (linha3.charAt(i) == '_') {
                String num = possivelNumeroContaLinha3(' ', linha3, linha1, linha2, i);
                if (verificarConta(num)) {
                    possiveis.add(num);
                } else {
                    num = possivelNumeroContaLinha3('|', linha3, linha1, linha2, i);
                    if (verificarConta(num)) {
                        possiveis.add(num);
                    }
                }
            }
        }
        /*
        *Se o ArrayList estiver vazio, quer dizer que não tem como corrigir o número
        *trocando UM pipe ou UM underscore
         */
        if (possiveis.isEmpty()) {
            possiveis.add(numeroConta(linha1, linha2, linha3));
        } else {
            return possiveis;
        }
        return possiveis;
    }

}
