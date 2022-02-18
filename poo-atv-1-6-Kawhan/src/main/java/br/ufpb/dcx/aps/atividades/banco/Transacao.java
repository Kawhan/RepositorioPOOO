package br.ufpb.dcx.aps.atividades.banco;

import java.util.Calendar;

public class Transacao {

    public static final String DEBITO = "DÉBITO", CREDITO = "CRÉDITO";
    private int id;
    private Calendar data;
    private double valor;
    private String tipo;

    public Transacao(int id, double valor, String tipo) {
        if(id < 0) throw new IllegalArgumentException("Id menor que zero");
        this.id = id;
        this.valor = valor;
        this.data = Calendar.getInstance();
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public double getValor() {
        return this.valor;
    }

    public int getID() {
        return this.id;
    }

    public Calendar getDataTransacao() {
        return this.data;
    }

    @Override
    public String toString() {
        return "ID: "+this.id+"\n"+"Data de transação: "+this.data+"\n"+"Valor da transação: "+this.valor;
    }
}
