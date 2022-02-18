package br.ufpb.dcx.aps.strategy.form.validador;

import br.ufpb.dcx.aps.strategy.form.Resultado;
import br.ufpb.dcx.aps.strategy.form.ValidadorInteiro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorInteiroTest {


    @Test
    void testInstanciaValidadorCampo() {
        ValidadorInteiro val = new ValidadorInteiro();
        assertTrue(val instanceof ValidadorInteiro);
    }

    @Test
    void testCasoSimples() {
        ValidadorInteiro validador = new ValidadorInteiro();
        Resultado res = validador.validarCampo("12301233");
        assertFalse(res.isErro());
        assertTrue(res.getMensagens().isEmpty());

        res = validador.validarCampo("-234234234");
        assertFalse(res.isErro());
        assertTrue(res.getMensagens().isEmpty());
    }

    @Test
    void testLimites() {

        ValidadorInteiro val = new ValidadorInteiro(10,1000);
        Resultado res = val.validarCampo("500");
        assertFalse(res.isErro());
        res = val.validarCampo("10");
        assertFalse(res.isErro());
        res = val.validarCampo("1000");
        assertFalse(res.isErro());

        res = val.validarCampo("9");
        assertTrue(res.isErro());
        assertEquals("valor menor que 10:9",res.getMensagens().get(0));

        res = val.validarCampo("1001");
        assertTrue(res.isErro());
        assertEquals("valor maior que 1000:1001",res.getMensagens().get(0));

        res = val.validarCampo("asdfas");
        assertTrue(res.isErro());
        assertEquals("valor não é um inteiro:'asdfas'",res.getMensagens().get(0));


    }
}
