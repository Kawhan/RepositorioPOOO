package br.ufpb.dcx.aps.atividades.banco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorrentistaTest {



    @Test
    @DisplayName("Teste igualdade entre Correntistas")
    void testEquals() {
        // Correntistas são iguais se seus CPFs são iguais
        Correntista c1 = new Correntista("86497624503", "Nome A");
        Correntista c2 = new Correntista("86497624503", "Nome B");
        assertEquals(c2, c1);

        c1 = new Correntista("44923833602", "Nome A");
        c2 = new Correntista("86497624503", "Nome A");
        assertNotEquals(c2, c1);
    }

    @Test
    void testValidaCPF() {
        assertFalse(Correntista.cpfValido(""));
        assertFalse(Correntista.cpfValido("00000000000"));
        assertFalse(Correntista.cpfValido("11111111111"));
        assertFalse(Correntista.cpfValido("22222222222"));
        assertFalse(Correntista.cpfValido("33333333333"));

        assertTrue(Correntista.cpfValido("44923833602"));
        assertTrue(Correntista.cpfValido("86497624503"));
        assertTrue(Correntista.cpfValido("864.976.245-03"));
    }

    @Test
    void testRemoveSeparadores(){
        assertEquals("44923833602",Correntista.removeSeparadores("449.238.336-02"));
        assertEquals("12345",Correntista.removeSeparadores("1.2.3.4.5"));
        assertEquals("12345",Correntista.removeSeparadores("1-2-3-4-5"));
    }
}
