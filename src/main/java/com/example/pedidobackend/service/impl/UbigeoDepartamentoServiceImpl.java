package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.UbigeoDepartamento;
import com.example.pedidobackend.repository.UbigeoDepartamentoRepository;
import com.example.pedidobackend.service.UbigeoDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbigeoDepartamentoServiceImpl implements UbigeoDepartamentoService {

    @Autowired
    private UbigeoDepartamentoRepository ubigeoDepartamentoRepository;

    @Override
    public List<UbigeoDepartamento> obtenerTodos() {
        return ubigeoDepartamentoRepository.findByEstadoTrue();
    }

}
