package com.example.pedidobackend.util.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoBusquedaRequestDTO {

    private String cliente;
    private Long estadoPedidoId;
    private Long operarioId;
    private Integer max;
    private Integer limite;

}
