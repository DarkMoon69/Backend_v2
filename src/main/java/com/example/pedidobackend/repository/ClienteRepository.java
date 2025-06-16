package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByTipoDocumentoIdAndNumeroDocumentoAndEstadoTrue(Long tipoDocumento_id, String numeroDocumento);

}
