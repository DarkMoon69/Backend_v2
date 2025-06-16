package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Long> {

    List<EstadoPedido> findByEstadoTrue();

}
