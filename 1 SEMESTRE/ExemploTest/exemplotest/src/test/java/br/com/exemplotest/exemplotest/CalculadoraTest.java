package br.com.exemplotest.exemplotest;

import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    
    @Test
    void testAdd(){

        Calculadora calculadora = new Calculadora();
        assertEquals(2,calculadora.add(a:1, b:1), "1 +1 deveria ser 2");
    }

    @Test
    void testMultiply(){
        Calculadora calculadora = new Calculadora();
        assertEquals(6, calculadora.multiply(a:2, 3), "2 * 3 deveria ser 6");
    }
    
}
