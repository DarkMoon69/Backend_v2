package com.example.pedidobackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pedidobackend.entity.Vehiculo;
import com.example.pedidobackend.repository.VehiculoRepository;
import com.example.pedidobackend.service.IVehiculoService;
import com.example.pedidobackend.util.dto.VehiculoDTO;

@Service
public class VehiculoServiceImpl implements IVehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public List<Vehiculo> filtrarVehiculos(String placa, String marca, String tipoVehiculo, Integer anio) {
        return vehiculoRepository.filtrarVehiculos(placa, marca, tipoVehiculo, anio);
    }

    @Override
    public Vehiculo guardarVehiculo(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(vehiculoDTO.getPlaca());
        vehiculo.setMarca(vehiculoDTO.getMarca());
        vehiculo.setTipoVehiculo(vehiculoDTO.getTipoVehiculo());
        vehiculo.setFotografia(vehiculoDTO.getFotografia());
        vehiculo.setAnio(vehiculoDTO.getAnio());

        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo actualizarVehiculo(Long id, VehiculoDTO vehiculoDTO) {
        Optional<Vehiculo> vehiculoExistente = vehiculoRepository.findById(id);
        if (vehiculoExistente.isPresent()) {
            Vehiculo vehiculo = vehiculoExistente.get();
            vehiculo.setPlaca(vehiculoDTO.getPlaca());
            vehiculo.setMarca(vehiculoDTO.getMarca());
            vehiculo.setTipoVehiculo(vehiculoDTO.getTipoVehiculo());
            vehiculo.setFotografia(vehiculoDTO.getFotografia());
            vehiculo.setAnio(vehiculoDTO.getAnio());
            vehiculo.setEstado(vehiculoDTO.getEstado());

            return vehiculoRepository.save(vehiculo);
        }
        return null;
    }

    @Override
    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

    @Override
    public Optional<Vehiculo> obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id);
    }
}
