package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.UbigeoDistrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbigeoDistritoRepository extends JpaRepository<UbigeoDistrito, Long> {

    List<UbigeoDistrito> findByUbigeoProvinciaIdAndEstadoTrue(Long ubigeoProvincia_id);

}
