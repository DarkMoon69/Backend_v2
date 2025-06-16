package com.example.pedidobackend.controller;

import com.example.pedidobackend.service.EstadoPedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/estadoPedido")
public class EstadoPedidoController {

    @Autowired
    private EstadoPedidoService estadoPedidoService;

    @GetMapping("/all")
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(estadoPedidoService.obtenerTodos());
    }

}
