package br.ufpb.dcx.aps.atividades.atv05;

public class Campo {
    private String id;
    private String nome;
    private String valor;

    public Campo(String id) {
        this.id = id;
        this.nome = "";
    }

    public Campo(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPreenchido (){
        return valor != null;
    }

    public void setValor (String valorNovo){
        this.valor = valorNovo;
    }

    public String getValor() {
        return valor;
    }
}
