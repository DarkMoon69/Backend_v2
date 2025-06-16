package com.example.pedidobackend.service;

import com.example.pedidobackend.entity.Cliente;

public interface ClienteService {

    Cliente buscarPorTipoDocumentoYNumero(Long tipoDocumId, String numeroDocumento);

}
