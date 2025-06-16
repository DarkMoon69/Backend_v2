package com.example.pedidobackend.controller;

import com.example.pedidobackend.util.dto.VehiculoDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedidobackend.entity.Vehiculo;
import com.example.pedidobackend.service.IVehiculoService;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping("/all")
    public ResponseEntity<List<Vehiculo>> listarVehiculos() {
        return ResponseEntity.ok(vehiculoService.listarVehiculos());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Vehiculo>> filtrarVehiculos(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String tipoVehiculo,
            @RequestParam(required = false) Integer anio) {
        return ResponseEntity.ok(vehiculoService.filtrarVehiculos(placa, marca, tipoVehiculo, anio));
    }

    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        log.info("Actualizando vehículo con ID: {}", vehiculoDTO);
        return ResponseEntity.ok(vehiculoService.guardarVehiculo(vehiculoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Long id, @RequestBody VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculoActualizado = vehiculoService.actualizarVehiculo(id, vehiculoDTO);
        if (vehiculoActualizado != null) {
            return ResponseEntity.ok(vehiculoActualizado);
        }

        log.error("Vehículo con ID {} no encontrado para actualización", id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable Long id) {
        return vehiculoService.obtenerVehiculoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/seed-data")
    public ResponseEntity<String> cargarData() {
        List<VehiculoDTO> datosPrueba = Arrays.asList(
                new VehiculoDTO("ABC123", "Toyota", "Sedán", "foto1.jpg", 2020, true),
                new VehiculoDTO("DEF456", "Honda", "SUV", "foto2.jpg", 2022, true),
                new VehiculoDTO("GHI789", "Ford", "Camioneta", "foto3.jpg", 2019, true));

        datosPrueba.forEach(vehiculoService::guardarVehiculo);

        return ResponseEntity.ok("¡Datos de prueba insertados correctamente!");
    }

}
