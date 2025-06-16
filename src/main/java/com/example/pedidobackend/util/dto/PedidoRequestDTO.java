package com.example.pedidobackend.util.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PedidoRequestDTO {

    private Long clienteId;
    private Long tipoDocumentoId;
    private String numeroDocumento;
    private String celular;
    private String apePaterno;
    private String apeMaterno;
    private String nombre;
    private String direccion;
    private String email;
    private Long operarioId;
    private Long vehiculoId;
    private LocalDate fechaPedido;
    private List<ProductoRequestDTO> productos;

}
