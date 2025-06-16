package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.UbigeoProvincia;
import com.example.pedidobackend.repository.UbigeoProvinciaRepository;
import com.example.pedidobackend.service.UbigeoProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbigeoProvinciaServiceImpl implements UbigeoProvinciaService {

    @Autowired
    private UbigeoProvinciaRepository ubigeoProvinciaRepository;

    @Override
    public List<UbigeoProvincia> obtenerProvinciasPorDepartamento(Long idDepartamento) {
        return ubigeoProvinciaRepository.findByUbigeoDepartamentoIdAndEstadoTrue(idDepartamento);
    }

}
