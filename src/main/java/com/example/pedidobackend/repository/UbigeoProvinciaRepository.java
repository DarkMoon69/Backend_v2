package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.UbigeoProvincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbigeoProvinciaRepository extends JpaRepository<UbigeoProvincia, Long> {

    List<UbigeoProvincia> findByUbigeoDepartamentoIdAndEstadoTrue(Long id);

}
