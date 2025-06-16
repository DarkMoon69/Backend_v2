package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.TipoDocumento;
import com.example.pedidobackend.repository.TipoDocumentoRepository;
import com.example.pedidobackend.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public List<TipoDocumento> obtenerTodos() {
        return tipoDocumentoRepository.findAll();
    }
}
