package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findByCodigoSkuAndEstadoTrue(String codigoSku);

}
