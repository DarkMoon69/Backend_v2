package com.example.pedidobackend.util.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequestDTO {

    private Long productoId;
    private String codigo;
    private String producto;
    private Integer cantidad;

}
