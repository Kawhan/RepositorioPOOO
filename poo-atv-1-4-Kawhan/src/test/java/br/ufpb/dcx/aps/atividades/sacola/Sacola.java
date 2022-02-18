package test.java.br.ufpb.dcx.aps.atividades.sacola;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sacola {

    private Map<Produto,ItemSacola> itens = new HashMap<>();

    public void addProduto(Produto produto, int quant) {
        ItemSacola item = new ItemSacola(produto,quant);
        itens.put(produto,item);
    }

    public void addProduto(Produto produto) {
        ItemSacola item = new ItemSacola(produto);
    }

    public void setQuantidade(Produto produto, int quant) {
        for (ItemSacola i: this.itens.values()) {
            if(i.getProduto().equals(produto)) {
                i.setQuantidade(quant);
            }
        }
    }

    public Produto removerProduto(Produto produto) {
        for (Map.Entry<Produto,ItemSacola> p: this.itens.entrySet()) {
            if (p.getKey().equals(produto)) {
                itens.remove(p.getKey());
            }
        }
        return produto;
    }

    public ItemSacola getItem(String codigo) {
        for (Map.Entry<Produto,ItemSacola> p: this.itens.entrySet()) {
            if(p.getKey().getCodigo().equals(codigo)) {
                return p.getValue();
            }
        }
        return null;
    }

    public int quantProduto() {
        int cont = 0;
        for (Produto p : this.itens.keySet()) {
            cont++;
        }
        return cont;
    }

    public List<ItemSacola> getItens() {
        List<ItemSacola> item = new ArrayList<>();
        for(Map.Entry<Produto,ItemSacola> p: this.itens.entrySet()) {
            item.add(p.getValue());
        }
        return item;
    }

    public double getTotalSacola() {
        double total = 0.0;
        for(ItemSacola i: this.itens.values()) {
            total += i.totalItem();
        }
        return total;
    }
}