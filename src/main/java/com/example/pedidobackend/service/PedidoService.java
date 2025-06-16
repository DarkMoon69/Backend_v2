package com.example.pedidobackend.service;

import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.BusquedaResponseDTO;
import com.example.pedidobackend.util.dto.PedidoBusquedaRequestDTO;
import com.example.pedidobackend.util.dto.PedidoRequestDTO;

public interface PedidoService {

    PedidoRequestDTO getById(Long id);

    BusquedaResponseDTO busquedaPaginada(PedidoBusquedaRequestDTO dto);

    RespuestaControlador guardar(PedidoRequestDTO pedidoRequestDTO);

    RespuestaControlador editar(Long pedidoId, PedidoRequestDTO pedidoRequestDTO);

    RespuestaControlador eliminar(Long id);

}
