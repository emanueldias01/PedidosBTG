package br.com.emanueldias01.pedidosbtg.model;

import br.com.emanueldias01.pedidosbtg.model.dto.PedidoRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;
    private Long codigoPedido;
    private Long codigoCliente;
    private List<Item> itens = new ArrayList<>();

    public Pedido() {

    }

    public Pedido(PedidoRequestDTO dto) {
        this.codigoCliente = dto.codigoCliente();
        this.codigoPedido = dto.codigoPedido();
        this.itens = dto.itens().stream().map(Item::new).toList();
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public String getId() {
        return id;
    }

    public List<Item> getItens() {
        return itens;
    }
}
