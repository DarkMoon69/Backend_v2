package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.EstadoPedido;
import com.example.pedidobackend.repository.EstadoPedidoRepository;
import com.example.pedidobackend.service.EstadoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService {

    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    @Override
    public List<EstadoPedido> obtenerTodos() {
        return estadoPedidoRepository.findByEstadoTrue();
    }
}
