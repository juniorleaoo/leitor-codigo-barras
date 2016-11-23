package Teste;

import banco.TransformarDados;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juniorleao
 */
public class TesteBanco {

    public TesteBanco() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    /*************************TRANSFORMAR DADOS*************************/
    @Test
    public void testandoNumeros() {
        TransformarDados obj = new TransformarDados();
        assertEquals("1", obj.numero("   ", "  |", "  |"));
        assertEquals("2", obj.numero(" _ ", " _|", "|_ "));        
        assertEquals("3", obj.numero(" _ ", " _|", " _|"));        
        assertEquals("4", obj.numero("   ", "|_|", "  |"));        
        assertEquals("5", obj.numero(" _ ", "|_ ", " _|"));        
        assertEquals("6", obj.numero(" _ ", "|_ ", "|_|"));        
        assertEquals("7", obj.numero(" _ ", "  |", "  |"));        
        assertEquals("8", obj.numero(" _ ", "|_|", "|_|"));        
        assertEquals("9", obj.numero(" _ ", "|_|", " _|"));        
        assertEquals("0", obj.numero(" _ ", "| |", "|_|"));
    }

    @Test//Gera uma unica correcao
    public void testandoCorrecaoDoNumeroDaConta() {
        TransformarDados obj = new TransformarDados();        
        assertEquals("123456789",obj.corrigirNumero("    _  _     _  _  _  _  _ ",
                                                    " _| _| _||_||_ |_   ||_||_|",
                                                    "  ||_  _|  | _||_|  ||_| _|").get(0));
    }
    
    @Test //Gerando várias correções
    public void testandoVariasCorrecoes(){
        TransformarDados obj = new TransformarDados(); 
        
        assertEquals("490867715",obj.corrigirNumero("    _  _  _  _  _  _     _ ",
                                                    "|_||_|| || ||_   |  |  ||_ ",
                                                    "  | _||_||_||_|  |  |  | _|").get(0));
        assertEquals("490067115",obj.corrigirNumero("    _  _  _  _  _  _     _ ",
                                                    "|_||_|| || ||_   |  |  ||_ ",
                                                    "  | _||_||_||_|  |  |  | _|").get(1));
        assertEquals("490067719",obj.corrigirNumero("    _  _  _  _  _  _     _ ",
                                                    "|_||_|| || ||_   |  |  ||_ ",
                                                    "  | _||_||_||_|  |  |  | _|").get(2));
        
    }
    
    @Test
    public void testandoNumeroConta(){
        TransformarDados obj = new TransformarDados(); 
        assertEquals("123456789",obj.numeroConta("    _  _     _  _  _  _  _ ",
                                                 "  | _| _||_||_ |_   ||_||_|",
                                                 "  ||_  _|  | _||_|  ||_| _|"));
    }
    
    @Test 
    public void testeDeVerificacao(){
        TransformarDados obj = new TransformarDados(); 
        //VERDADEIRO
        assertEquals(true,obj.verificarConta("123456789"));
        //FALSE
        assertEquals(false,obj.verificarConta("023456789"));
    }
}
