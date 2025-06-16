package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    List<DetallePedido> findByPedidoIdAndEstadoTrue(Long pedidoId);

}
