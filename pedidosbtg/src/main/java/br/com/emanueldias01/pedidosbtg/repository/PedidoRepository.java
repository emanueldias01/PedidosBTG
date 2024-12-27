package br.com.emanueldias01.pedidosbtg.repository;

import br.com.emanueldias01.pedidosbtg.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "SELECT * FROM tab_pedidos WHERE codigo_cliente=:codigoCliente", nativeQuery = true)
    List<Pedido> buscaPedidosDoCliente(@Param("codigoCliente") Long codigoCliente);
}
