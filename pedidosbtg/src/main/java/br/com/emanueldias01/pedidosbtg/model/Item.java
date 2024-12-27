package br.com.emanueldias01.pedidosbtg.model;

import br.com.emanueldias01.pedidosbtg.model.dto.ItemDTO;

public class Item {
    private String produto;
    private Integer quantidade;
    private Double preco;


    public Double getPreco() {
        return preco;
    }

    public String getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Item(ItemDTO item) {
        this.produto = item.produto();
        this.quantidade = item.quantidade();
        this.preco = item.preco();
    }
}
