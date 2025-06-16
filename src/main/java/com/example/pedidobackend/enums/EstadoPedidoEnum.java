package com.example.pedidobackend.enums;

import lombok.Getter;

@Getter
public enum EstadoPedidoEnum {

    PENDIENTE(1L, "PENDIENTE"),
    EN_CAMINO(2L, "EN CAMINO"),
    ENTREGADO(3L, "ENTREGADO"),
    ANULADO(4L, "ANULADO"),
    ASIGNADO(5L, "ASIGNADO");

    private Long id;
    private String nombre;

    EstadoPedidoEnum(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
