package test.java.br.ufpb.dcx.aps.atividades.sacola;

import java.math.BigDecimal;

public class Produto {

    private String codigo;
    private String nome;
    private BigDecimal preco;

    public Produto(String codigo,String nome) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = new BigDecimal(0.0);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return this.codigo;
    }


    public double getPreco() {
        return this.preco.doubleValue();
    }

    public void setPreco(double valor) throws RuntimeException{
        if(valor >= 0) {
            this.preco = new BigDecimal(valor);
        } else {
            throw new RuntimeException("preco não pode ser negativo: " +valor);
        }
    }

    public boolean equals(Produto produto) {
        return this.codigo.equals(produto.getCodigo());
    }
}
