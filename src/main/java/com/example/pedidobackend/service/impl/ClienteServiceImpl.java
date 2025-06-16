package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.Cliente;
import com.example.pedidobackend.repository.ClienteRepository;
import com.example.pedidobackend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente buscarPorTipoDocumentoYNumero(Long tipoDocumId, String numeroDocumento) {
        return clienteRepository.findByTipoDocumentoIdAndNumeroDocumentoAndEstadoTrue(tipoDocumId, numeroDocumento);
    }

}
