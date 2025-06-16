package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = " SELECT cli.nombrecompleto as cliente, cli.direccion as direccion, " +
            " pe.id_pedido as nropedido, op.nombrecompleto as operario, esp.nombre as estadopedido, pe.id_pedido as pedidoid " +
            " FROM pedido pe " +
            " INNER JOIN cliente cli ON cli.id_cliente = pe.id_cliente " +
            " LEFT OUTER JOIN operario op ON op.id_operario = pe.id_operario " +
            " INNER JOIN estado_pedido esp ON esp.id_estado_pedido = pe.id_estado_pedido " +
            " WHERE pe.estado " +
            " AND (:cliente IS NULL OR lower(cli.nombres) LIKE lower(concat('%', :cliente, '%'))) " +
            " AND (:estadoPedidoId IS NULL OR esp.id_estado_pedido = :estadoPedidoId) " +
            " AND (:operarioId IS NULL OR op.id_operario = :operarioId) " +
            " ORDER BY pe.id_pedido " +
            " LIMIT :maximo OFFSET :limite "
            ,nativeQuery = true)
    List<Map<String, Object>> busquedaPaginadaPedido(
            @Param("cliente") String cliente,
            @Param("estadoPedidoId") Long estadoPedidoId,
            @Param("operarioId") Long operarioId,
            @Param("maximo") Integer maximo,
            @Param("limite") Integer limite);

    @Query(value = "SELECT COUNT(pe.id_pedido) " +
            " FROM pedido pe " +
            " INNER JOIN cliente cli ON cli.id_cliente = pe.id_cliente " +
            " INNER JOIN operario op ON op.id_operario = pe.id_operario " +
            " INNER JOIN estado_pedido esp ON esp.id_estado_pedido = pe.id_estado_pedido " +
            " WHERE pe.estado " +
            " AND (:cliente IS NULL OR lower(cli.nombres) LIKE lower(concat('%', :cliente, '%'))) " +
            " AND (:estadoPedidoId IS NULL OR esp.id_estado_pedido = :estadoPedidoId) " +
            " AND (:operarioId IS NULL OR op.id_operario = :operarioId) " +
            " ORDER BY pe.id_pedido " +
            " LIMIT :maximo OFFSET :limite "
            ,nativeQuery = true)
    Integer busquedaPaginadaPedidoContar(
            @Param("cliente") String cliente,
            @Param("estadoPedidoId") Long estadoPedidoId,
            @Param("operarioId") Long operarioId,
            @Param("maximo") Integer maximo,
            @Param("limite") Integer limite);

}
