package br.com.exemplotest.exemplotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    
    @Test
    void testAdd(){

        Calculadora calculadora = new Calculadora();
        assertEquals(2,calculadora.add(1, 1), "1 + 1 deveria ser 2");
    }

    @Test
    void testMultiply(){
        Calculadora calculadora = new Calculadora();
        assertEquals(6, calculadora.multiply(2, 3), "2 * 3 deveria ser 6");
    }
    
}
