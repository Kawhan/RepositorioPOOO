package br.ufpb.dcx.aps.strategy.form.validador;

import br.ufpb.dcx.aps.strategy.form.Resultado;
import br.ufpb.dcx.aps.strategy.form.ValidadorCampo;
import br.ufpb.dcx.aps.strategy.form.ValidadorEmail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorEmailTest {


    @Test
    void testImplements() {
        assertTrue(new ValidadorEmail() instanceof ValidadorCampo);
    }

    @Test
    void testSimples() {

        ValidadorEmail val = new ValidadorEmail();

        assertFalse(val.validarCampo("fulano@dcx.ufpb.br").isErro());


        Resultado res = val.validarCampo("alskdfj");
        assertTrue(res.isErro());
        assertEquals("email inválido: 'alskdfj'",res.getMensagens().get(0));
    }
}
