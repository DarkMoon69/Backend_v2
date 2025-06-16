package com.example.pedidobackend.service;

import com.example.pedidobackend.entity.Producto;
import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.PedidoRequestDTO;

public interface ProductoService {

    Producto buscarPorCodigoSku(String codigoSku);

}
