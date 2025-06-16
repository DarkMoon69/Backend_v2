package com.example.pedidobackend.service;

import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.LoginRequestDTO;

public interface UsuarioService {

    RespuestaControlador validarLogin(LoginRequestDTO loginRequestDTO);

}
