package br.com.emanueldias01.pedidosbtg.service;

import br.com.emanueldias01.pedidosbtg.model.Pedido;
import br.com.emanueldias01.pedidosbtg.model.dto.*;
import br.com.emanueldias01.pedidosbtg.repository.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PedidoServiceTest {
    @Mock
    PedidoRepository pedidoRepository;

    @InjectMocks
    PedidoService pedidoService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar um pedido")
    void criaPedido(){
        ObjectMapper mapper = new ObjectMapper();

        String json = """
                {
                  "codigoPedido": 12345,
                  "codigoCliente": 67890,
                  "itens": [
                    {
                      "produto": "Produto A",
                      "quantidade": 2,
                      "preco": 19.99
                    },
                    {
                      "produto": "Produto B",
                      "quantidade": 1,
                      "preco": 45.50
                    }
                  ]
                }
                """;

        try {
            PedidoRequestDTO dtoArgumento = mapper.readValue(json, PedidoRequestDTO.class);
            PedidoResponseDTO dtoResult = pedidoService.criaPedido(dtoArgumento);

            List<ItemDTO> itemList = new ArrayList<>();
            itemList.add(new ItemDTO("Produto A", 2, 19.99));
            itemList.add(new ItemDTO("Produto B", 1, 45.50));

            assertThat(dtoResult.codigoCliente()).isEqualTo(67890L);
            assertThat(dtoResult.codigoPedido()).isEqualTo(12345L);
            assertThat(dtoResult.itens()).isEqualTo(itemList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Busca o valor total de um pedido pelo id")
    void buscaValorTotalDoPedidoPeloId(){
        String id = "231623612";

        List<ItemDTO> itemList = new ArrayList<>();
        itemList.add(new ItemDTO("Produto A", 2, 19.99));
        itemList.add(new ItemDTO("Produto B", 1, 45.50));
        Pedido pedido = new Pedido(new PedidoRequestDTO(2123L, 3727L, itemList));

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        ValorTotalDTO result = new ValorTotalDTO(id, 85.48);

        assertThat(pedidoService.buscaValorTotalDoPedido(id).id()).isEqualTo(id);
        assertThat(pedidoService.buscaValorTotalDoPedido(id).valorTotal()).isEqualTo(result.valorTotal());
    }

    @Test
    @DisplayName("busca numero total de pedidos do cliente")
    void buscaTotalDePedidosDoCliente(){
        String id = "231623612";

        List<ItemDTO> itemList = new ArrayList<>();
        itemList.add(new ItemDTO("Produto A", 2, 19.99));
        itemList.add(new ItemDTO("Produto B", 1, 45.50));
        Pedido pedido1 = new Pedido(new PedidoRequestDTO(2123L, 3727L, itemList));
        Pedido pedido2 = new Pedido(new PedidoRequestDTO(2124L, 3727L, itemList));

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);

        when(pedidoRepository.buscaPedidosDoCliente(3727L)).thenReturn(pedidos);

        TotalPedidosClienteDTO result = new TotalPedidosClienteDTO(3727L, 2);

        assertThat(pedidoService.buscaTotalDePedidosPorCliente(3727L).codigoCliente()).isEqualTo(3727L);
        assertThat(pedidoService.buscaTotalDePedidosPorCliente(3727L).quantidadeDePedidos()).isEqualTo(2);

    }

    @Test
    @DisplayName("deve buscar todos os pedidos do cliente")
    void buscaTodosOsPedidosDoCliente(){
        String id = "231623612";

        List<ItemDTO> itemList = new ArrayList<>();
        itemList.add(new ItemDTO("Produto A", 2, 19.99));
        itemList.add(new ItemDTO("Produto B", 1, 45.50));
        Pedido pedido1 = new Pedido(new PedidoRequestDTO(2123L, 3727L, itemList));
        Pedido pedido2 = new Pedido(new PedidoRequestDTO(2124L, 3727L, itemList));

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);

        when(pedidoRepository.buscaPedidosDoCliente(3727L)).thenReturn(pedidos);

        List<PedidoResponseDTO> pedidosDTO = pedidos.stream().map(PedidoResponseDTO::new).toList();

        assertThat(pedidoService.buscaPedidosDoCliente(3727L)).isEqualTo(pedidosDTO);

    }

}