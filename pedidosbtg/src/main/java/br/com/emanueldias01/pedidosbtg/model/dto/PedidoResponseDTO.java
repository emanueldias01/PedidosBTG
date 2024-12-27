package br.com.emanueldias01.pedidosbtg.model.dto;

import br.com.emanueldias01.pedidosbtg.model.Item;
import br.com.emanueldias01.pedidosbtg.model.Pedido;

import java.util.List;

public record PedidoResponseDTO(
        String id,
        Long codigoPedido,
        Long codigoCliente,
        List<ItemDTO> itens
) {
    public PedidoResponseDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getCodigoPedido(), pedido.getCodigoCliente(), pedido.getItens().stream().map(ItemDTO::new).toList());
    }
}
