package test.java.br.ufpb.dcx.aps.atividades.sacola;

public class ItemSacola {

    private Produto produto;
    private int quantidade;

    public ItemSacola(Produto produto) {
        this.produto = produto;
        this.quantidade = 1;
    }

    public ItemSacola(Produto produto, int quant) {
        this.produto = produto;
        this.quantidade = quant;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setQuantidade(int quant) {
        this.quantidade = quant;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double totalItem() {
        return this.quantidade*produto.getPreco();
    }
}