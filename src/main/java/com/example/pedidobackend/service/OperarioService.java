package com.example.pedidobackend.service;

import com.example.pedidobackend.entity.Operario;
import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.OperarioBusquedaRequestDTO;
import com.example.pedidobackend.util.dto.BusquedaResponseDTO;

import java.util.List;

public interface OperarioService {

    RespuestaControlador guardar (Operario operario);

    RespuestaControlador actualizar(Operario operario);

    RespuestaControlador eliminar(Long id);

    Operario getById(Long id);

    BusquedaResponseDTO busquedaPaginada(OperarioBusquedaRequestDTO dto);

    List<Operario> obtenerTodos();

}
