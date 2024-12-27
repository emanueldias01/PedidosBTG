package br.com.emanueldias01.pedidosbtg.model.dto;

import java.util.List;

public record PedidoRequestDTO(
        Long codigoPedido,
        Long codigoCliente,
        List<ItemDTO> itens
) {
}
