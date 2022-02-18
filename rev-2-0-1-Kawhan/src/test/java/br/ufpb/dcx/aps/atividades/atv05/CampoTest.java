package br.ufpb.dcx.aps.atividades.atv05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {

    Campo campo;

    @BeforeEach
    void setUp() {
        campo = new Campo("campo");
    }

    @Test
    void testBasico() {
        assertEquals("campo",campo.getId());
        assertEquals("",campo.getNome());
        campo.setNome("Campo teste");
        assertEquals("Campo teste",campo.getNome());

        Campo c2 = new Campo("c2","Campo C2");
        assertEquals("c2",c2.getId());
        assertEquals("Campo C2",c2.getNome());
    }

    @Test
    void testCampoPreenchido() {
        //Quando um campo receber um valor, ele passa a ser "preenchido"
        // ou seja, o método "isPreenchido" só retorna true depois do campo
        // receber um valor
        assertFalse(campo.isPreenchido());
        campo.setValor("Valor Campo");
        assertTrue(campo.isPreenchido());

        assertEquals("Valor Campo",campo.getValor());
    }
}
