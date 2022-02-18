package br.ufpb.dcx.aps.strategy.form;

public class ValidadorTextoSimples implements ValidadorCampo{

    private int max;
    private int min;

    public ValidadorTextoSimples(int min, int max) {
        if(min <= 0 || max <= 0) {
            throw new IllegalArgumentException("invalido min="+min+", max="+max);
        }
        this.min = min;
        this.max = max;
    }

    public ValidadorTextoSimples() {
        this.min = 0;
        this.max = 0;
    }

    public Resultado validarCampo(String valor) {
        if(valor == null) {
            return new Resultado(true,"valor: "+valor);
        } else if(this.max < valor.length()) {
            return new Resultado(true, "ERRO: tamanho do valor > "+this.max+": '"+valor+"'");
        } else if(this.min > valor.length()) {
            return new Resultado(true,"ERRO: tamanho do valor < "+this.min+": '"+valor+"'");
        }
        return new Resultado();
    }
}