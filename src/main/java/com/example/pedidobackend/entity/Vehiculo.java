package com.example.pedidobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@ToString
@Table(name = "vehiculo")
public class Vehiculo extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long id;

    @Column(name = "placa", length = 50)
    private String placa;

    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "tipo_vehiculo", length = 50)
    private String tipoVehiculo;

    @Column(name = "fotografia", length = 255)
    private String fotografia;

    @Column(name = "año", length = 50)
    private Integer anio;

}
