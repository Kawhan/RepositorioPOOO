package br.ufpb.dcx.aps.strategy.form;

public class ValidadorEmail implements ValidadorCampo {

    public Resultado validarCampo(String valor) {
        if(!valor.contains("@")) {
            return new Resultado(true,"email inválido: '"+valor+"'" );
        }
        return new Resultado();
    }
}