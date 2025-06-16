package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.UbigeoDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbigeoDepartamentoRepository extends JpaRepository<UbigeoDepartamento, Long> {

    List<UbigeoDepartamento> findByEstadoTrue();

}
