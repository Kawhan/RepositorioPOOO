package br.ufpb.dcx.aps.strategy.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoTest {


    Campo campo;
    @BeforeEach
    void setUp() {
        campo = new Campo("campo");
    }


    @Test
    void testBasico() {
        Resultado resultado = campo.validar();
        // por padrão um resultado não é um erro
        assertFalse(resultado.isErro());
        // um resultado inicia sem mensagens
        assertTrue(resultado.getMensagens().isEmpty());

        resultado.setErro(true);
        assertTrue(resultado.isErro());

        resultado.addMensagem("mensagem 1");
        List<String> msg = resultado.getMensagens();
        assertEquals(1,msg.size());
        assertEquals("mensagem 1",msg.get(0));
    }

    @Test
    void testResultado() {

        campo.setObrigatorio(true);
        Resultado resultado = campo.validar();

        // Como o campo é obrigatório e ele ainda não foi prenchido,
        // o resultado deve retornar true em "isErro()"
        assertTrue(resultado.isErro());
        List<String> msg = resultado.getMensagens();
        assertEquals("campo é obrigatório e não foi preenchido",msg.get(0));

        campo.setValor("Agora tem valor e o resultado não pode ter erro");
        assertFalse(campo.validar().isErro());
        assertTrue(campo.validar().getMensagens().isEmpty());

    }
}
