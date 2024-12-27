package br.com.emanueldias01.pedidosbtg.service;

import br.com.emanueldias01.pedidosbtg.model.Pedido;
import br.com.emanueldias01.pedidosbtg.model.dto.PedidoRequestDTO;
import br.com.emanueldias01.pedidosbtg.model.dto.PedidoResponseDTO;
import br.com.emanueldias01.pedidosbtg.model.dto.TotalPedidosClienteDTO;
import br.com.emanueldias01.pedidosbtg.model.dto.ValorTotalDTO;
import br.com.emanueldias01.pedidosbtg.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoResponseDTO criaPedido(PedidoRequestDTO dto){
        Pedido pedido = new Pedido(dto);
        pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }

    public ValorTotalDTO buscaValorTotalDoPedido(Long id){
        Pedido pedido = pedidoRepository.getReferenceById(id);

        AtomicReference<Double> totalPedido = new AtomicReference<>(0.0);
        pedido.getItens().stream().forEach(item -> {
            totalPedido.updateAndGet(v -> v + item.getPreco() * item.getQuantidade());
        });

        return new ValorTotalDTO(id, totalPedido.get());
    }

    public TotalPedidosClienteDTO buscaTotalDePedidosPorCliente(Long codigoCliente){
        List<Pedido> listaDePedidos = pedidoRepository.buscaPedidosDoCliente(codigoCliente);

        Integer quantidadeDePedidos = listaDePedidos.size();

        return new TotalPedidosClienteDTO(codigoCliente, quantidadeDePedidos);
    }

    public List<PedidoResponseDTO> buscaPedidosDoCliente(Long codigoCliente){
        return pedidoRepository.buscaPedidosDoCliente(codigoCliente).stream().map(PedidoResponseDTO::new).toList();
    }
}
