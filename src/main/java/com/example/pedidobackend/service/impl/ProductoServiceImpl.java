package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.Producto;
import com.example.pedidobackend.repository.ProductoRepository;
import com.example.pedidobackend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto buscarPorCodigoSku(String codigoSku) {
        return productoRepository.findByCodigoSkuAndEstadoTrue(codigoSku);
    }

}
