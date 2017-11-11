package main;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static final int MAXIMO_DIGITOS = 9;

	public List<String> numerosEntrada(List<String> entrada, int quantidadeLinhas) {

		List<String> resultadoFinal = new ArrayList<>();
		List<String> possiveisNumerosCorrigidos = new ArrayList<String>();
		String numeroConta = new String();

		for (int j = 0; j < quantidadeLinhas; j += 4) {
			numeroConta = getNumeroDigito(entrada.get(j), entrada.get(j+1), entrada.get(j+2));

			if (verificarNumero(numeroConta)) {
				resultadoFinal.add(numeroConta);

			} else {
				possiveisNumerosCorrigidos = corrigirNumero(entrada.get(j), entrada.get(j+1), entrada.get(j+2));
				int quantidadeNumerosCorrigidos = possiveisNumerosCorrigidos.size();
				
				if (numeroConta.contains("?")) {

					if (quantidadeNumerosCorrigidos > 1) {
						StringBuilder numero = new StringBuilder();
						numero.append(numeroConta).append(" AMB [");

						for (int i = 0; i < possiveisNumerosCorrigidos.size(); i++) {
							numero.append(possiveisNumerosCorrigidos.get(i)).append(" ");
						}

						numero.append("]");
						resultadoFinal.add(numero.toString());

					} else if (quantidadeNumerosCorrigidos == 1) {
						if (possiveisNumerosCorrigidos.get(0).contains("?")) {
							resultadoFinal.add(possiveisNumerosCorrigidos.get(0) + " ILL");

						} else {
							resultadoFinal.add(possiveisNumerosCorrigidos.get(0));

						}
					}

				} else {
					
					if (quantidadeNumerosCorrigidos > 1) {
						StringBuilder numero = new StringBuilder();
						numero.append(numeroConta).append(" AMB [");
						
						for (int i = 0; i < quantidadeNumerosCorrigidos; i++) {
							numero.append(possiveisNumerosCorrigidos.get(i)).append(" ");
						}
						numero.append("]");
						resultadoFinal.add(numero.toString());

					} else if (quantidadeNumerosCorrigidos == 1) {
						if (verificarNumero(possiveisNumerosCorrigidos.get(0))) {
							resultadoFinal.add(possiveisNumerosCorrigidos.get(0));
						} else {
							resultadoFinal.add(possiveisNumerosCorrigidos.get(0) + " ERR");
						}
					}
				}
				
			}
		}
		return resultadoFinal;
	}

	public String getNumeroDigito(String linha1, String linha2, String linha3) {
		String soma = new String();
		for (int i = 0; i < linha1.length(); i += 3) {
			soma += getNumero(linha1.substring(i, i + 3), linha2.substring(i, i + 3), linha3.substring(i, i + 3));
		}
		return soma;
	}

	public boolean verificarNumero(String numero) {
		int soma = 0;
		int j = 0;
		if (numero.contains("?") || numero.length() > MAXIMO_DIGITOS) {
			return false;
		} else {
			for (int i = 9; i > 0; i--) {
				soma += (i * Character.getNumericValue(numero.charAt(j++)));
			}
			return ((soma % 11) == 0);
		}
	}

	public String getNumero(String linha1, String linha2, String linha3) {
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
		}else {
			return "?";
		}
	}

	public String trocaCaracter(char troca, String linha1, int i) {
		StringBuilder aux = new StringBuilder(linha1);
		aux.setCharAt(i, troca);
		return aux.toString();
	}

	public String possivelNumeroLinha(char troca, String linha1, String linha2, String linha3, int posicao, int qualLinha) {
		String aux = trocaCaracter(troca, linha1, posicao);
		
		if(qualLinha == 1) {
			return getNumeroDigito(aux, linha2, linha3);
			
		} else if(qualLinha == 2) {
			return getNumeroDigito(linha2, aux, linha3);
			
		} else {
			return getNumeroDigito(linha2, linha3, aux);
			
		}
	}
	
	public List<String> corrigirNumero(String linha1, String linha2, String linha3) {

		List<String> possiveisNumeros = new ArrayList<String>();
		for (int i = 0; i < linha1.length(); i++) {
			
			/*LINHA 1*/
			if (linha1.charAt(i) == ' ') {
				String num = possivelNumeroLinha('_', linha1, linha2, linha3, i, 1);
				if (verificarNumero(num)) {
					possiveisNumeros.add(num);
				
				}
			
			}else if (linha1.charAt(i) == '_') {
				String num = possivelNumeroLinha(' ', linha1, linha2, linha3, i, 1);
				if (verificarNumero(num)) {
					possiveisNumeros.add(num);
				}
			}
			
			/*LINHA2*/
			if (linha2.charAt(i) == ' ') {
				String num = possivelNumeroLinha('|', linha2, linha1, linha3, i, 2);
				if (verificarNumero(num)) {
					possiveisNumeros.add(num);
				} else {
					num = possivelNumeroLinha('_', linha2, linha1, linha3, i, 2);
					if (verificarNumero(num)) {
						possiveisNumeros.add(num);
					}
				}
				
			} else if (linha2.charAt(i) == '|') {
				String num = possivelNumeroLinha(' ', linha2, linha1, linha3, i, 2);
				if (verificarNumero(num)) {
					possiveisNumeros.add(num);
				} else {
					num = possivelNumeroLinha(' ', linha2, linha1, linha3, i, 2);
					if (verificarNumero(num)) {
						possiveisNumeros.add(num);
					}
				}
				
			} else if (linha2.charAt(i) == '_') {
				String num = possivelNumeroLinha(' ', linha2, linha1, linha3, i, 2);
				if (verificarNumero(num)) {
					possiveisNumeros.add(num);
				} else {
					num = possivelNumeroLinha('|', linha2, linha1, linha3, i, 2);
					if (verificarNumero(num)) {
						possiveisNumeros.add(num);
					}
				}
			}
			
			/*LINHA 3*/
			if (linha3.charAt(i) == ' ') {
				String num = possivelNumeroLinha('|', linha3, linha1, linha2, i, 3);
				if (verificarNumero(num)) {
					possiveisNumeros.add(num);
				} else {
					num = possivelNumeroLinha('_', linha3, linha1, linha2, i, 3);
					if (verificarNumero(num)) {
						possiveisNumeros.add(num);
					}
				}
			
			} else if (linha3.charAt(i) == '|') {
				String num = possivelNumeroLinha(' ', linha3, linha1, linha2, i, 3);
				if (verificarNumero(num)) {
					possiveisNumeros.add(num);
				} else {
					num = possivelNumeroLinha('_', linha3, linha1, linha2, i, 3);
					if (verificarNumero(num)) {
						possiveisNumeros.add(num);
					}
				}
			
			} else if (linha3.charAt(i) == '_') {
				String num = possivelNumeroLinha(' ', linha3, linha1, linha2, i, 3);
				if (verificarNumero(num)) {
					possiveisNumeros.add(num);
				} else {
					num = possivelNumeroLinha('|', linha3, linha1, linha2, i, 3);
					if (verificarNumero(num)) {
						possiveisNumeros.add(num);
					}
				}
			}
		}

		if (possiveisNumeros.isEmpty()) {
			possiveisNumeros.add(getNumeroDigito(linha1, linha2, linha3));
		} else {
			return possiveisNumeros;
		}

		return possiveisNumeros;
	}
}
