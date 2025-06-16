package com.example.pedidobackend.controller;

import com.example.pedidobackend.entity.TipoDocumento;
import com.example.pedidobackend.service.TipoDocumentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/tipoDocumento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<?> obtenerTodos() {
        List<TipoDocumento> response = tipoDocumentoService.obtenerTodos();
        return ResponseEntity.ok(response);
    }

}
