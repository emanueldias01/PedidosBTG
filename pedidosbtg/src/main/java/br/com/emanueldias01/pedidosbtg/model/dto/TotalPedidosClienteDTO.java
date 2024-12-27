package br.com.emanueldias01.pedidosbtg.model.dto;

public record TotalPedidosClienteDTO(
        Long codigoCliente,
        Integer quantidadeDePedidos
) {
}
