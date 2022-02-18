package br.ufpb.dcx.aps.strategy.form;

public class ValidadorInteiro implements ValidadorCampo {
    private int valorMinimo;
    private int valorMaximo;

    public ValidadorInteiro(int valorMinimo, int valorMaximo){
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
    }

    public ValidadorInteiro(){}


    @Override
    public Resultado validarCampo(String valor){
        try{
            int valorInt = Integer.parseInt(valor);
            if(valorMaximo == 0 && valorMinimo == 0){
                Resultado re = new Resultado(false);
                return re;
            }
            else if(valorInt > valorMaximo){
                Resultado re = new Resultado(true, "valor maior que "+valorMaximo+":"+valorInt);
                return re;
            }else if(valorInt < valorMinimo){
                Resultado re = new Resultado(true, "valor menor que "+valorMinimo+":"+valorInt);
                return re;
            }else{
                Resultado re = new Resultado(false);
                return re;
            }
        }catch(NumberFormatException e){
            Resultado re = new Resultado(true, "valor não é um inteiro:'"+valor+"'");
            return re;
        }
    }
}