package com.example.pedidobackend.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {
    private String placa;
    private String marca;
    private String tipoVehiculo;
    private String fotografia;
    private Integer anio;
    private Boolean estado;
}
