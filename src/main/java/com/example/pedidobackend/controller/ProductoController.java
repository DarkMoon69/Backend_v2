package com.example.pedidobackend.controller;

import com.example.pedidobackend.service.ProductoService;
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
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/obtenerPorCodigoSKU")
    public ResponseEntity<?> obtenerPorCodigoSKU(@RequestParam(value = "codigoSKU") String codigoSKU) {
        return ResponseEntity.ok(productoService.buscarPorCodigoSku(codigoSKU));
    }

}
