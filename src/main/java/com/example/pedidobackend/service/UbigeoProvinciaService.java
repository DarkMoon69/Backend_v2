package com.example.pedidobackend.service;

import com.example.pedidobackend.entity.UbigeoProvincia;

import java.util.List;

public interface UbigeoProvinciaService {

     List<UbigeoProvincia> obtenerProvinciasPorDepartamento(Long idDepartamento);

}
