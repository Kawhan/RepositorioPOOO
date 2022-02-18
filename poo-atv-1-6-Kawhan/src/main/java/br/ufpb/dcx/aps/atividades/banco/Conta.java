package br.ufpb.dcx.aps.atividades.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Conta {

    private Correntista correntista;
    private int numero;
    private List<Transacao> transacoes;
    private Banco banco;
    private double saldo;

    public Conta(Correntista correntista, int numero, Banco banco) {
        this.correntista = correntista;
        this.numero = numero;
        this.banco = banco;
        this.transacoes = new ArrayList<>();
        this.saldo = 0.0;
    }

    public Conta(int numero) {
        this.numero = numero;
    }

    public Transacao depositar(double valor) {
        this.saldo+= valor;
        Transacao transacao = new Transacao(gerarID(), valor,Transacao.CREDITO);
        transacoes.add(transacao);
        return transacao;
    }

    public Transacao sacar(double valor) throws ContaException {
        if(valor > this.saldo) {
            throw new ContaException("Saldo insuficiente. Saldo:"+this.saldo+" - valor do saque:"+valor);
        }
        this.saldo-= valor;
        Transacao transacao = new Transacao(gerarID(), valor, Transacao.DEBITO);
        this.transacoes.add(transacao);
        return transacao;
    }

    public String view() {
        String s = "";
        String format = "";
        for (Transacao t : this.transacoes) {
            if(t.getTipo().equals(Transacao.DEBITO)) {
                format = Transacao.DEBITO+"\t-R$ "+String.format("%.2f", t.getValor())+"\n";
            } else {
                format = Transacao.CREDITO+"\tR$ "+String.format("%.2f", t.getValor())+"\n";
            }
            s+=format;
        }
        return s;
    }

    public String extrato() {
        return ">> " + banco.getNome() + "\n" +
                ">> Correntista: \n" +
                " CPF: " + correntista.getCPF() + "\n" +
                " " + correntista.getNome() + "\n" +
                "> EXTRATO\n" +
                "------------------------------------\n" +
                view() +
                "------------------------------------\n" +
                "SALDO:\tR$ "+String.format("%.2f", saldo);
    }


    public int gerarID() {
        Random gerador = new Random();
        return gerador.nextInt(9999);
    }

    public int getNumero() {
        return this.numero;
    }

    public double saldo() {
        return this.saldo;
    }

    public double getSaldo() {

        return this.saldo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return numero == conta.numero;
    }
}