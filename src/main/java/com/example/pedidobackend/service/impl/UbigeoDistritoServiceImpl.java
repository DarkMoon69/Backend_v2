package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.UbigeoDistrito;
import com.example.pedidobackend.repository.UbigeoDistritoRepository;
import com.example.pedidobackend.service.UbigeoDistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbigeoDistritoServiceImpl implements UbigeoDistritoService {

    @Autowired
    private UbigeoDistritoRepository ubigeoDistritoRepository;

    @Override
    public List<UbigeoDistrito> obtenerDistritosPorProvincia(Long idProvincia) {
        return ubigeoDistritoRepository.findByUbigeoProvinciaIdAndEstadoTrue(idProvincia);
    }

}
