package com.example.pedidobackend.controller;

import com.example.pedidobackend.entity.UbigeoProvincia;
import com.example.pedidobackend.service.UbigeoProvinciaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/ubigeoProvincia")
public class UbigeoProvinciaController {

    @Autowired
    private UbigeoProvinciaService ubigeoProvinciaService;

    @GetMapping("/departamento/{idDepartamento}")
    public ResponseEntity<?> obtenerProvinciasPorDepartamento(@PathVariable Long idDepartamento) {
        List<UbigeoProvincia> provincias = ubigeoProvinciaService.obtenerProvinciasPorDepartamento(idDepartamento);
        if (!provincias.isEmpty()) {
            return new ResponseEntity<>(provincias, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
