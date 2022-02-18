package br.ufpb.dcx.aps.atividades.banco;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Banco {
    
    private String nome;
    private Map<Integer,Conta> contas;
    private static int numeroContaLivre = 20;
    private Map<String,Correntista> correntistas;

    private static int gerarNumeroConta() {
        Random gerador = new Random();
        return gerador.nextInt(9999);
    }


    public Banco(String nome ) {
        this.nome = nome;
        this.contas = new HashMap<>();
        this.correntistas = new HashMap<>();
    }

    public Conta criarConta(Correntista correntista) throws BancoException {
        if(this.correntistas.get(correntista.getCPF()) == null) {
            throw new BancoException("Correntista não cadastrado no banco:"+correntista.toString());
        }
        if(correntista.getConta() != null) {
            throw new BancoException("Correntista já tem conta cadastrada");
        }
        Conta novaConta = new Conta(correntista, gerarNumeroConta(), this);
        correntista.setConta(novaConta);
        contas.put(novaConta.getNumero(), novaConta);
        numeroContaLivre = numeroContaLivre-1;
        return novaConta;
    }

    public void addCorrentista(Correntista correntista) throws BancoException {
        if(!Correntista.cpfValido(correntista.getCPF())) {
            throw new RuntimeException("CPF invalido:"+correntista.getCPF());
        } else if(this.correntistas.get(correntista.getCPF()) == null) {
            this.correntistas.put(correntista.getCPF(), correntista);
        } else {
            throw new RuntimeException("Correntista ja cadastrado:"+correntista.toString());
        }
    }
    
    public Conta getConta(int num) {
        return contas.get(num);
    }

    public String getNome() {
        return this.nome;
    }

    public Conta getConta(Correntista titular) {
        return contas.get(titular.getConta().getNumero());
    }

    public Correntista getCorrentista(String cpf) {
        if(correntistas.get(cpf) != null) {
            return this.correntistas.get(cpf);
        }
        throw new RuntimeException("Não existe correntista com cpf:"+cpf);
    }
}