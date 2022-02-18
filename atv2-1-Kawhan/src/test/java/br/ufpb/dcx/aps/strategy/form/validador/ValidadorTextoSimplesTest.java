package br.ufpb.dcx.aps.strategy.form.validador;

import br.ufpb.dcx.aps.strategy.form.Resultado;
import br.ufpb.dcx.aps.strategy.form.ValidadorCampo;
import br.ufpb.dcx.aps.strategy.form.ValidadorTextoSimples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorTextoSimplesTest {

    ValidadorTextoSimples validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorTextoSimples();
    }


    @Test
    void testImplementacaoInterface() {
        assertTrue(validador instanceof ValidadorCampo, "ValidadorTextoSimples deve implementar ValidadorCampo");
    }

    @Test
    void testCasoSimples() {

        validador = new ValidadorTextoSimples();

        Resultado res = validador.validarCampo("");
        assertFalse(res.isErro());


        res = validador.validarCampo(null);
        assertTrue(res.isErro());
        assertTrue(res.getMensagens().size() == 1);
        assertEquals("valor: null",res.getMensagens().get(0));
    }

    @Test
    void testLimiteTamanho() {

        validador = new ValidadorTextoSimples(5,10);

        Resultado res = validador.validarCampo("abcde");
        assertFalse(res.isErro());
        res = validador.validarCampo("abcdefghij");
        assertFalse(res.isErro());

        res = validador.validarCampo("a");
        assertTrue(res.isErro());
        assertEquals("ERRO: tamanho do valor < 5: 'a'",res.getMensagens().get(0));

        res = validador.validarCampo("abcdefghijk");
        assertTrue(res.isErro());
        assertEquals("ERRO: tamanho do valor > 10: 'abcdefghijk'",res.getMensagens().get(0));


    }

    @Test
    void testValidadorInvalido() {

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new ValidadorTextoSimples(-4,0) );
        assertEquals("invalido min=-4, max=0", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () ->
                new ValidadorTextoSimples(0,-4) );
        assertEquals("invalido min=0, max=-4", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () ->
                new ValidadorTextoSimples(-4,-4) );
        assertEquals("invalido min=-4, max=-4", e.getMessage());
    }
}
