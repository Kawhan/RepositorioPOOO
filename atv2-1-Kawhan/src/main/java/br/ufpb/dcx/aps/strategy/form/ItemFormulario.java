package br.ufpb.dcx.aps.strategy.form;

public interface ItemFormulario {

    String getId();
    void setValor(String valor);
    Resultado validar();
    void setObrigatorio(boolean resp);
    boolean isObrigatorio();
    boolean isPreenchido();
    String getNome();
}
