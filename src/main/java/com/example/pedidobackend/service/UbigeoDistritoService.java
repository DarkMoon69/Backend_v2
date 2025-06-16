package com.example.pedidobackend.service;

import com.example.pedidobackend.entity.UbigeoDistrito;

import java.util.List;

public interface UbigeoDistritoService {

    List<UbigeoDistrito> obtenerDistritosPorProvincia(Long idProvincia );

}
