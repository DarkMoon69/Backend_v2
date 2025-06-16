package com.example.pedidobackend.service;

import java.util.List;
import java.util.Optional;

import com.example.pedidobackend.entity.Vehiculo;
import com.example.pedidobackend.util.dto.VehiculoDTO;

public interface IVehiculoService {
    List<Vehiculo> listarVehiculos();

    List<Vehiculo> filtrarVehiculos(String placa, String marca, String tipoVehiculo, Integer anio);

    Vehiculo guardarVehiculo(VehiculoDTO vehiculoDTO);

    Vehiculo actualizarVehiculo(Long id, VehiculoDTO vehiculoDTO);

    void eliminarVehiculo(Long id);

    Optional<Vehiculo> obtenerVehiculoPorId(Long id);
}
