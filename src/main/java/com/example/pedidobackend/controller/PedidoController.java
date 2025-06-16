package com.example.pedidobackend.controller;

import com.example.pedidobackend.service.PedidoService;
import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.PedidoBusquedaRequestDTO;
import com.example.pedidobackend.util.dto.PedidoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/busquedaPagina")
    public ResponseEntity<?> busquedaPaginada(@RequestBody PedidoBusquedaRequestDTO dto) {
        return ResponseEntity.ok(pedidoService.busquedaPaginada(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerOperarioPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pedidoService.getById(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar (@RequestBody PedidoRequestDTO requestDTO){
        try {
            RespuestaControlador rc = pedidoService.guardar(requestDTO);
            return ResponseEntity.ok(rc);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/editar/{idPedido}")
    public ResponseEntity<?> guardar (@PathVariable Long idPedido, @RequestBody PedidoRequestDTO requestDTO){
        try {
            RespuestaControlador rc = pedidoService.editar(idPedido, requestDTO);
            return ResponseEntity.ok(rc);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/eliminar/{idPedido}")
    public ResponseEntity<?> anular(@PathVariable Long idPedido) {
        RespuestaControlador rc = pedidoService.eliminar(idPedido);
        return ResponseEntity.ok(rc);
    }

}
