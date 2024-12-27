package br.com.emanueldias01.pedidosbtg.service;

import br.com.emanueldias01.pedidosbtg.model.Pedido;
import br.com.emanueldias01.pedidosbtg.model.dto.PedidoRequestDTO;
import br.com.emanueldias01.pedidosbtg.model.dto.PedidoResponseDTO;
import br.com.emanueldias01.pedidosbtg.repository.PedidoRepository;
import org.springframework.stereotype.Service;

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
}
