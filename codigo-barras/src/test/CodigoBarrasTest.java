package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.Util;

class CodigoBarrasTest {

	@Test
    public void testandoNumeros() {
        Util obj = new Util();
        assertEquals("1", obj.getNumero("   ",
        							 "  |", 
        							 "  |"));
        assertEquals("2", obj.getNumero(" _ ", 
        							 " _|", 
        							 "|_ "));        
        assertEquals("3", obj.getNumero(" _ ",
        							 " _|", 
        							 " _|"));        
        assertEquals("4", obj.getNumero("   ", 
        							 "|_|", 
        							 "  |"));        
        assertEquals("5", obj.getNumero(" _ ", 
        							 "|_ ", 
        							 " _|"));        
        assertEquals("6", obj.getNumero(" _ ", 
        							 "|_ ", 
        							 "|_|"));        
        assertEquals("7", obj.getNumero(" _ ", 
        							 "  |", 
        							 "  |"));        
        assertEquals("8", obj.getNumero(" _ ", 
        							 "|_|", 
        							 "|_|"));        
        assertEquals("9", obj.getNumero(" _ ", 
        							 "|_|", 
        							 " _|"));        
        assertEquals("0", obj.getNumero(" _ ", 
        							 "| |", 
        							 "|_|"));
    }

    @Test
    public void testandoCorrecaoUnica() {
        Util obj = new Util();        
        assertEquals("123456789",obj.corrigirNumero("    _  _     _  _  _  _  _ ",
                                                    " _| _| _||_||_ |_   ||_||_|",
                                                    "  ||_  _|  | _||_|  ||_| _|").get(0));
    }
    
    @Test
    public void testandoVariasCorrecoes(){
        Util obj = new Util(); 
        
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
    public void testandoNumero(){
        Util obj = new Util(); 
        assertEquals("123456789",obj.getNumeroDigito("    _  _     _  _  _  _  _ ",
        											 "  | _| _||_||_ |_   ||_||_|",
                                                 	 "  ||_  _|  | _||_|  ||_| _|"));
    }
    
    @Test 
    public void testeDeVerificacao(){
        Util obj = new Util(); 
        assertEquals(true,obj.verificarNumero("123456789"));
        assertEquals(false,obj.verificarNumero("023456789"));
    }

}
