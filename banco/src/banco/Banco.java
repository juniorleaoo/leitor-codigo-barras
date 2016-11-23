package banco;
/**
 *
 * @author juniorleao
 */
public class Banco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String nome = "entrada.txt";
            CRUD objetoArquivo = new CRUD();
            TransformarDados objetoDados = new TransformarDados();
            telaApresentacao();
            resultadoFinal(objetoArquivo,objetoDados,nome);
        } catch (IllegalArgumentException e) {
            System.out.println("A conta tem mais que 9 digitos");
        }

    }
    
    public static void telaApresentacao(){        
        System.out.println("O nome do arquivo de entrada é: entrada.txt \n"
                         + "O nome do arquivo de saida é: saida.txt\n"
                         + "Observação: O local padrão é o ECXUS\\banco\\entrada.txt e ECXUS\\banco\\saida.txt");

    }
    
    public static void resultadoFinal(CRUD objetoArquivo, TransformarDados objetoDados,String nome) {
        String[] saida = objetoDados.numerosEntrada(objetoArquivo.lerArquivo(nome), objetoArquivo.quantidadeLinhas(nome));
        objetoArquivo.escreverArquivo(saida);
    }

}
