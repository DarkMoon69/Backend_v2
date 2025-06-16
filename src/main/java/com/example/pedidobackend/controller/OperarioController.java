package com.example.pedidobackend.controller;

import com.example.pedidobackend.entity.Operario;
import com.example.pedidobackend.service.OperarioService;
import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.OperarioBusquedaRequestDTO;
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
@RequestMapping("/api/operario")
public class OperarioController {

    @Autowired
    private OperarioService operarioService;

    @PostMapping("/busquedaPagina")
    public ResponseEntity<?> busquedaPaginada(@RequestBody OperarioBusquedaRequestDTO dto) {
        return ResponseEntity.ok(operarioService.busquedaPaginada(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerOperarioPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(operarioService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(operarioService.obtenerTodos());
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar (@RequestBody Operario operario){
        try {
            RespuestaControlador rc = operarioService.guardar(operario);
            return ResponseEntity.ok(rc);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/editar/{idOperario}")
    public ResponseEntity<?> editar(@PathVariable Long idOperario, @RequestBody Operario newOperario) {
        Operario operarioDb = operarioService.getById(idOperario);
        if (operarioDb != null) {
            operarioDb.setApePaterno(newOperario.getApePaterno());
            operarioDb.setApeMaterno(newOperario.getApeMaterno());
            operarioDb.setNombres(newOperario.getNombres());
            operarioDb.setNumeroCelular(newOperario.getNumeroCelular());
            operarioDb.setTipoDocumento(newOperario.getTipoDocumento());
            operarioDb.setNumeroDocumento(newOperario.getNumeroDocumento());
            operarioDb.setCorreoElectronico(newOperario.getCorreoElectronico());
            operarioDb.setUbigeoDistrito(newOperario.getUbigeoDistrito());
            operarioDb.setDireccion(newOperario.getDireccion());
            operarioDb.setNumeroBrevete(newOperario.getNumeroBrevete());
            operarioDb.setCategoriaBrevete(newOperario.getCategoriaBrevete());
            operarioDb.setFechaVencimientoBrevete(newOperario.getFechaVencimientoBrevete());
            RespuestaControlador rc = operarioService.actualizar(operarioDb);
            return ResponseEntity.ok(rc);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/eliminar/{idOperario}")
    public ResponseEntity<?> anular(@PathVariable Long idOperario) {
        RespuestaControlador rc = operarioService.eliminar(idOperario);
        return ResponseEntity.ok(rc);
    }

}
