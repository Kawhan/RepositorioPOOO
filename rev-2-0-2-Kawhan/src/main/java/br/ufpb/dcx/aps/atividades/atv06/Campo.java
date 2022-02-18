package br.ufpb.dcx.aps.atividades.atv06;

public class Campo {

    private String nome;
    private String id;
    private String valor;
    private boolean obrigatorio;

    public Campo(String id) {
        this.id = id;
        this.nome = "";
        this.obrigatorio = false;
    }

    public Campo(String id, boolean obrigatorio) {
        this.id = id;
        this.obrigatorio = obrigatorio;
        this.nome = "";
    }

    public Campo(String id, String nome) {
        this.nome = nome;
        this.id = id;
        this.obrigatorio = false;
    }

    public Campo(String id, boolean obrigatorio, String nome) {
        this.id = id;
        this.obrigatorio = obrigatorio;
        this.nome = nome;
    }

    public String getId() {
        return  this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setValor(String novoValor) {
        this.valor = novoValor;
    }

    public boolean isPreenchido() {
        return this.valor != null;
    }

    public  boolean isObrigatorio() {
        return this.obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public String getValor() {
        return this.valor;
    }

    public Resultado validar() {
        Resultado result = new Resultado();
        if(this.obrigatorio && this.valor == null) {
            result.setErro(true);
            result.addMensagem("Este campo é obrigatório e não foi preenchido");
            return result;
        }
        return result;
    }
}
