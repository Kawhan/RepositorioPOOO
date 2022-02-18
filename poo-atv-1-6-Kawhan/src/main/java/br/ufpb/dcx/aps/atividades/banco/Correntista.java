package br.ufpb.dcx.aps.atividades.banco;

import java.util.Objects;

public class Correntista {

    private String cpf;
    private String nome;
    private Conta conta;

    public static boolean cpfValido(String cp) {
        int cont = 0;
        String [] cpf = removeSeparadores(cp).split("");
        if(cp.equals("")) {
            return false;
        }
        for(int k=0; k<cpf.length; k++) {
            if(k==cpf.length-1) {
                if(cpf[k].equals(cpf[k-1])) {
                    cont++;
                }
            } else if(cpf[k].equals(cpf[k+1])) {
                cont++;
            }
        }
        if(cont == cpf.length) {
            return false;
        }
        return true;
    }

    public static String removeSeparadores(String cp) {
        String remove = cp.replace("-","");
        String remove2 = remove.replace(".","");
        return remove2;
    }

    public Correntista(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public Correntista(String cpf) {
        this.cpf = cpf;
    }

    public String getCPF() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return this.conta;
    }


    @Override
    public String toString() {
        return "Correntista{cpf='" + cpf + "', nome='" + nome + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Correntista correntistas = (Correntista) o;
        return cpf.equals(correntistas.cpf);
    }
}