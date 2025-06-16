package com.example.pedidobackend.controller;

import com.example.pedidobackend.entity.UbigeoDistrito;
import com.example.pedidobackend.service.UbigeoDistritoService;
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
@RequestMapping("/api/ubigeoDistrito")
public class UbigeoDistritoController {

    @Autowired
    private UbigeoDistritoService ubigeoDistritoService;

    @GetMapping("/provincia/{idProvincia}")
    public ResponseEntity<?> obtenerDistritosPorProvincia(@PathVariable Long idProvincia) {
        List<UbigeoDistrito> distritos = ubigeoDistritoService.obtenerDistritosPorProvincia(idProvincia);
        if (!distritos.isEmpty()) {
            return new ResponseEntity<>(distritos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
