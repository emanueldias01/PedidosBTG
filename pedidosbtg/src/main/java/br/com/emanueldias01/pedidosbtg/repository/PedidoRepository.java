package br.com.emanueldias01.pedidosbtg.repository;

import br.com.emanueldias01.pedidosbtg.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    @Query("{ 'codigoCliente': ?0 }")
    List<Pedido> buscaPedidosDoCliente(Long codigoCliente);
}
