package com.example.pedidobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pedidobackend.entity.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    @Query("SELECT v FROM Vehiculo v WHERE " +
            "(:placa IS NULL OR v.placa LIKE %:placa%) " +
            "AND (:marca IS NULL OR v.marca LIKE %:marca%) " +
            "AND (:tipoVehiculo IS NULL OR v.tipoVehiculo LIKE %:tipoVehiculo%) " +
            "AND (:anio IS NULL OR v.anio = :anio)")
    List<Vehiculo> filtrarVehiculos(String placa, String marca, String tipoVehiculo, Integer anio);
}
