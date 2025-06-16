package com.example.pedidobackend.controller;

import com.example.pedidobackend.entity.UbigeoDepartamento;
import com.example.pedidobackend.service.UbigeoDepartamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/ubigeoDepartamento")
public class UbigeoDepartamentoController {

    @Autowired
    private UbigeoDepartamentoService ubigeoDepartamentoService;

    @GetMapping("/all")
    public List<UbigeoDepartamento> obtenerUbigeoDepart() {
        return ubigeoDepartamentoService.obtenerTodos();
    }

}
