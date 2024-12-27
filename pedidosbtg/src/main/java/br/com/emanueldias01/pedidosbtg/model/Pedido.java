package br.com.emanueldias01.pedidosbtg.model;

import br.com.emanueldias01.pedidosbtg.model.dto.ItemDTO;
import br.com.emanueldias01.pedidosbtg.model.dto.PedidoRequestDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tab_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public Long getId() {
        return id;
    }

    public List<Item> getItens() {
        return itens;
    }
}
