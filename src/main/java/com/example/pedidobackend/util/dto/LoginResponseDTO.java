package com.example.pedidobackend.util.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseDTO {

    private String nombreUsuarioLogueado;
    private String numeroIdentificacionUsuarioLogueado;
    private List<ObjetosMenuResponseDTO> detalle;

}
