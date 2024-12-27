package br.com.emanueldias01.pedidosbtg.model.dto;

import br.com.emanueldias01.pedidosbtg.model.Item;

public record ItemDTO(
        String produto,
        Integer quantidade,
        Double preco
) {
    public ItemDTO(Item item) {
        this(item.getProduto(), item.getQuantidade(), item.getPreco());
    }
}
