package com.example.pedidobackend.controller;

import com.example.pedidobackend.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/obtenerPorTipoNumeroDocumento")
    public ResponseEntity<?> obtenerPorTipoNumeroDocumento( @RequestParam(value = "tipoDocumentoId") Long tipoDocumentoId,
                                                            @RequestParam(value = "numeroDocumento") String numeroDocumento) {
        return ResponseEntity.ok(clienteService.buscarPorTipoDocumentoYNumero(tipoDocumentoId, numeroDocumento));
    }

}
