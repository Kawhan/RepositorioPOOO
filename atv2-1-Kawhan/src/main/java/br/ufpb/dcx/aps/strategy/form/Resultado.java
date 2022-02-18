package br.ufpb.dcx.aps.strategy.form;

import java.util.ArrayList;
import java.util.List;


public class Resultado {

    private List<String> mensagens;
    private boolean erro;

    public Resultado() {
        this(false);
    }

    public Resultado(boolean erro) {
        this.mensagens = new ArrayList<>();
        this.erro = erro;
    }

    public Resultado(boolean erro, String msg) {
        this.mensagens = new ArrayList<>();
        this.mensagens.add(msg);
        this.erro = erro;
    }

    public void addMensagem(String msg) {
        this.mensagens.add(msg);
    }

    public List<String> getMensagens() {
        return this.mensagens;
    }

    public boolean isErro() {
        return this.erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

}