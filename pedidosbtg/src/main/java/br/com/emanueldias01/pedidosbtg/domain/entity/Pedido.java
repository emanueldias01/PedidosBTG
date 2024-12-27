package br.com.emanueldias01.pedidosbtg.domain.entity;

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
}
