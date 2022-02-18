package br.ufpb.dcx.aps.atividades.banco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BancoFacadeTest {

    BancoFacade bancoLimpo, bancoIniciado;
    Conta contaExistente;

    @BeforeEach
    void setUp() {
        bancoLimpo = new BancoFacade("Banco Limpo SA");

        bancoIniciado = new BancoFacade("Banco Iniciado SA");
        try {
            bancoIniciado.cadastrarCorrentista("44923833602", "Ze do Teste");
        } catch (BancoException e) {
            fail(e);
        }
        try {
            contaExistente = bancoIniciado.criarContaPF("44923833602");
        } catch (BancoException e) {
            fail(e);
        }
    }

    @Test
    void testCadastroCorrentista() {
        // Correntista com CPF inválido:
        Exception exception = assertThrows(RuntimeException.class, () ->
                bancoLimpo.cadastrarCorrentista("00000000000","teste"));
        assertEquals("CPF invalido:00000000000", exception.getMessage());

        exception = assertThrows(RuntimeException.class, () ->
                bancoLimpo.cadastrarCorrentista("xxxx","teste"));
        assertEquals("CPF invalido:xxxx", exception.getMessage());


        try {
            bancoLimpo.cadastrarCorrentista("44923833602", "nome");
        } catch (BancoException e) {
            fail("cadastrarCorrentista lançou exceção quando não deveria");
        }
        // Tentando cadastrar um correntista já existente
        exception = assertThrows(RuntimeException.class, () ->
                bancoLimpo.cadastrarCorrentista("44923833602", "João"));
        assertEquals("Correntista ja cadastrado:Correntista{cpf='44923833602', nome='João'}", exception.getMessage());

    }

    @Test
    void testCriarContaPF() {
        try {
            bancoLimpo.cadastrarCorrentista("44923833602", "nome");
        } catch (BancoException e) {
            fail("Não deveria ter lançado exceção");
        }

        Exception exception = assertThrows(RuntimeException.class, () ->
                bancoLimpo.criarContaPF("86497624503"));
        assertEquals("Não existe correntista com cpf:86497624503", exception.getMessage());


        Conta conta = null;
        try {
            conta = bancoLimpo.criarContaPF("44923833602");
        }catch (Exception e){
            fail("Não deveria ter lançado exceção: "+e.getMessage());
        }
        exception = assertThrows(BancoException.class, () ->
                bancoLimpo.criarContaPF("44923833602"));
        assertEquals("Correntista já tem conta cadastrada", exception.getMessage());

        assertNotNull(conta);
        assertEquals(conta, bancoLimpo.getConta(conta.getNumero()));

        assertNull(bancoLimpo.getConta(123));


    }

    @Test
    void testDepositarSacarSaldo() throws ContaException {

        assertEquals(0.0,bancoIniciado.saldo(contaExistente.getNumero()));

        bancoIniciado.depositar(10.0,contaExistente.getNumero());
        assertEquals(10.0,bancoIniciado.saldo(contaExistente.getNumero()));

        bancoIniciado.sacar(5.0,contaExistente.getNumero());
        assertEquals(5.0,bancoIniciado.saldo(contaExistente.getNumero()));

        Exception exception = assertThrows(ContaException.class, () ->
                bancoIniciado.sacar(10,contaExistente.getNumero()));
        assertEquals("Saldo insuficiente. Saldo:5.0 - valor do saque:10.0", exception.getMessage());
    }

    @Test
    void testExtratoSaldoMaiorQueZero() throws ContaException {

        bancoIniciado.depositar(10.0,contaExistente.getNumero());
        bancoIniciado.depositar(10.0,contaExistente.getNumero());
        bancoIniciado.depositar(10.0,contaExistente.getNumero());
        bancoIniciado.depositar(10.0,contaExistente.getNumero());
        bancoIniciado.sacar(5.0,contaExistente.getNumero());
        bancoIniciado.sacar(5.0,contaExistente.getNumero());
        bancoIniciado.sacar(5.0,contaExistente.getNumero());
        String extrato = ">> Banco Iniciado SA\n" +
                ">> Correntista: \n" +
                " CPF: 44923833602\n" +
                " Ze do Teste\n" +
                "> EXTRATO\n" +
                "------------------------------------\n" +
                "CRÉDITO\tR$ 10,00\n" +
                "CRÉDITO\tR$ 10,00\n" +
                "CRÉDITO\tR$ 10,00\n" +
                "CRÉDITO\tR$ 10,00\n" +
                "DÉBITO\t-R$ 5,00\n" +
                "DÉBITO\t-R$ 5,00\n" +
                "DÉBITO\t-R$ 5,00\n" +
                "------------------------------------\n" +
                "SALDO:\tR$ 25,00";
        assertEquals(extrato,bancoIniciado.extrato(contaExistente.getNumero()));

    }


    @Test
    void testExtratoSaldoZero(){

        String extrato = ">> Banco Iniciado SA\n" +
                ">> Correntista: \n" +
                " CPF: 44923833602\n" +
                " Ze do Teste\n" +
                "> EXTRATO\n" +
                "------------------------------------\n" +
                "------------------------------------\n" +
                "SALDO:\tR$ 0,00";
        assertEquals(extrato,bancoIniciado.extrato(contaExistente.getNumero()));

    }



}
