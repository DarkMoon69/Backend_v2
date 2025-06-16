package com.example.pedidobackend.util.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperarioBusquedaRequestDTO {

    private String numeroDocumento;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long tipoDocumentoId;
    private String estadoOperario;
    private Integer max;
    private Integer limite;

}
