package test.java.br.ufpb.dcx.aps.atividades.my1stTest;

public class Produto {
    private String nome;
    private String descricao;
    private double preco;

    public Produto() {
        this.nome = "";
        this.preco = 0.0;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double valor) throws RuntimeException {
        if (valor >= 0) {
            this.preco = valor;
        } else {
            throw new RuntimeException("preco não pode ser negativo: " + valor);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }
}
