package br.ufpb.dcx.aps.strategy.form;

public class Campo implements ItemFormulario {

    private String id;
    private String nome;
    private boolean obrigatorio;
    private String valor;
    private ValidadorCampo validador;

    public Campo(String id) {
        this(id,"");
    }

    public Campo(String id, String nome) {
        this.nome = nome;
        this.id = id;
        this.valor = "";
    }

    public Campo(String id, boolean obrigatorio) {
        this(id,obrigatorio,"");
    }

    public Campo(String id, boolean obrigatorio, String nome) {
        this.id = id;
        this.obrigatorio = obrigatorio;
        this.nome = nome;
        this.valor = "";
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
        return !this.valor.equals("");
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

    @Override
    public Resultado validar() {
        if(this.obrigatorio && !this.isPreenchido()) {
            return new Resultado(true, getId()+" é obrigatório e não foi preenchido");
        } else if(!isObrigatorio() && !isPreenchido() || validador == null){
            return new Resultado();
        }
        return this.validador.validarCampo(this.valor);
    }

    public void setValidador(ValidadorCampo validador) {
        this.validador = validador;
    }
}