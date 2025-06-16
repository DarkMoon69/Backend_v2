package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.Operario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OperarioRepository extends JpaRepository<Operario, Long> {

    @Query(value = " SELECT op.id_operario as operadorId, CONCAT(op.ape_paterno,' ',op.ape_materno,' ',op.nombres) as persona, " +
            " op.numero_documento as numDocumento, op.numero_brevete as brevete, op.categoria_brevete as categoria, case when op.estado_operario = 'A' then 'ACTIVO' else 'INACTIVO' end as estadoOperario " +
            " FROM operario op " +
            " LEFT OUTER JOIN tipo_documento tdoc ON tdoc.id_tipo_documento = op.id_tipo_documento " +
            " WHERE op.estado " +
            " AND (:nombre IS NULL OR lower(op.nombres) LIKE lower(concat('%', :nombre, '%'))) " +
            " AND (:apellidoPaterno IS NULL OR lower(op.ape_paterno) LIKE lower(concat('%', :apellidoPaterno, '%'))) " +
            " AND (:apellidoMaterno IS NULL OR lower(op.ape_materno) LIKE lower(concat('%', :apellidoMaterno, '%'))) " +
            " AND (:tipoDocumentoId IS NULL OR tdoc.id_tipo_documento = :tipoDocumentoId) " +
            " AND (:numeroDocumento IS NULL OR op.numero_documento = :numeroDocumento) " +
            " AND (:estadoOperario IS NULL OR op.estado_operario = :estadoOperario) " +
            " ORDER BY op.ape_paterno, op.ape_materno, op.nombres" +
            " LIMIT :maximo OFFSET :limite "
            ,nativeQuery = true)
    List<Map<String, Object>> busquedaPaginadaOperario(
            @Param("nombre") String nombre,
            @Param("apellidoPaterno") String apellidoPaterno,
            @Param("apellidoMaterno") String apellidoMaterno,
            @Param("tipoDocumentoId") Long tipoDocumentoId,
            @Param("numeroDocumento") String numeroDocumento,
            @Param("estadoOperario") String estadoOperario,
            @Param("maximo") Integer maximo,
            @Param("limite") Integer limite);

    @Query(value = "SELECT COUNT(op.id_operario) " +
            "FROM operario op " +
            "LEFT OUTER JOIN tipo_documento tdoc ON tdoc.id_tipo_documento = op.id_tipo_documento " +
            "WHERE op.estado " +
            "AND (:nombre IS NULL OR lower(op.nombres) LIKE lower(concat('%', :nombre, '%'))) " +
            "AND (:apellidoPaterno IS NULL OR lower(op.ape_paterno) LIKE lower(concat('%', :apellidoPaterno, '%'))) " +
            "AND (:apellidoMaterno IS NULL OR lower(op.ape_materno) LIKE lower(concat('%', :apellidoMaterno, '%'))) " +
            "AND (:tipoDocumentoId IS NULL OR tdoc.id_tipo_documento = :tipoDocumentoId) " +
            "AND (:numeroDocumento IS NULL OR op.numero_documento = :numeroDocumento) " +
            "AND (:estadoOperario IS NULL OR op.estado_operario = :estadoOperario) " +
            "LIMIT :maximo OFFSET :limite "
            ,nativeQuery = true)
    Integer busquedaPaginadaOperarioContar(
            @Param("nombre") String nombre,
            @Param("apellidoPaterno") String apellidoPaterno,
            @Param("apellidoMaterno") String apellidoMaterno,
            @Param("tipoDocumentoId") Long tipoDocumentoId,
            @Param("numeroDocumento") String numeroDocumento,
            @Param("estadoOperario") String estadoOperario,
            @Param("maximo") Integer maximo,
            @Param("limite") Integer limite);

    List<Operario> findByEstadoTrue();


}
