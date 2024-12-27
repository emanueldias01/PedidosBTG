package br.com.emanueldias01.pedidosbtg.controller;

import br.com.emanueldias01.pedidosbtg.model.dto.PedidoResponseDTO;
import br.com.emanueldias01.pedidosbtg.model.dto.TotalPedidosClienteDTO;
import br.com.emanueldias01.pedidosbtg.model.dto.ValorTotalDTO;
import br.com.emanueldias01.pedidosbtg.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;


    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/totalPedido/{codigoPedido}")
    public ResponseEntity<ValorTotalDTO> buscaValorTotalDoPedido(@PathVariable String codigoPedido){
        return ResponseEntity.ok(pedidoService.buscaValorTotalDoPedido(codigoPedido));
    }

    @GetMapping("/quantidadePedidos/{codigoCliente}")
    public ResponseEntity<TotalPedidosClienteDTO> buscaTotalDePedidosDoCliente(@PathVariable Long codigoCliente){
        return ResponseEntity.ok(pedidoService.buscaTotalDePedidosPorCliente(codigoCliente));
    }

    @GetMapping("/{codigoCliente}")
    public ResponseEntity<List<PedidoResponseDTO>> buscaPedidosDoCliente(@PathVariable Long codigoCliente){
        return ResponseEntity.ok(pedidoService.buscaPedidosDoCliente(codigoCliente));
    }
}
