package br.com.emanueldias01.pedidosbtg.repository;

import br.com.emanueldias01.pedidosbtg.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
