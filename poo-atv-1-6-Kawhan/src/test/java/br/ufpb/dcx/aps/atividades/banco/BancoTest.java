package br.ufpb.dcx.aps.atividades.banco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BancoTest {

    Banco banco;

    @BeforeEach
    void setUp() {
        banco = new Banco("nome");
    }

    @Test
    void testBanco() {

        Correntista joao = new Correntista("78468806323","JoaoTeste");

        Exception exception = assertThrows(BancoException.class, () ->
                banco.criarConta(joao));
        assertEquals("Correntista não cadastrado no banco:Correntista{cpf='78468806323', nome='JoaoTeste'}", exception.getMessage());

        assertEquals("nome", banco.getNome());
    }
}
