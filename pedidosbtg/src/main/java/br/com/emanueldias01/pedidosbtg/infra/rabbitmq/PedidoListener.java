package br.com.emanueldias01.pedidosbtg.infra.rabbitmq;

import br.com.emanueldias01.pedidosbtg.model.Pedido;
import br.com.emanueldias01.pedidosbtg.model.dto.PedidoRequestDTO;
import br.com.emanueldias01.pedidosbtg.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final PedidoService pedidoService;

    public PedidoListener (PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = "pedidos")
    public void pedidoProcessadoListener(String pedidoJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PedidoRequestDTO dto = mapper.readValue(pedidoJson, PedidoRequestDTO.class);

        pedidoService.criaPedido(dto);
    }
}
