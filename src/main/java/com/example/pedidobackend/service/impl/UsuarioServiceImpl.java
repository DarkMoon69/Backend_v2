package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.Usuario;
import com.example.pedidobackend.repository.UsuarioRepository;
import com.example.pedidobackend.service.UsuarioService;
import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.LoginRequestDTO;
import com.example.pedidobackend.util.dto.LoginResponseDTO;
import com.example.pedidobackend.util.dto.ObjetosMenuResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public RespuestaControlador validarLogin(LoginRequestDTO loginRequestDTO) {
        final String mensajeError = "Credenciales incorrectas";
        RespuestaControlador respuestaControlador;
        Usuario usuario = usuarioRepository.findByNombreUsuarioAndEstadoTrue(loginRequestDTO.getUsuario());
        if (usuario != null) {
            if (usuario.getContraseniaHash().equals(loginRequestDTO.getPassword())) {
                List<ObjetosMenuResponseDTO> listado = obtenerMenuUsuarioLogueado();
                LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
                loginResponseDTO.setDetalle(listado);
                if (usuario.getOperario() != null) {
                    loginResponseDTO.setNumeroIdentificacionUsuarioLogueado(usuario.getOperario().getNumeroDocumento());
                    loginResponseDTO.setNombreUsuarioLogueado(usuario.getOperario().getNombrecompleto());
                } else {
                    loginResponseDTO.setNombreUsuarioLogueado("ADMINISTRADOR");
                    loginResponseDTO.setNumeroIdentificacionUsuarioLogueado("ADMINISTRADOR");
                }
                respuestaControlador = RespuestaControlador.obtenerRespuestaExitoConExtraInfo(loginResponseDTO);
            } else {
                respuestaControlador = RespuestaControlador.obtenerRespuestaDeError(mensajeError);
            }
        } else {
            respuestaControlador = RespuestaControlador.obtenerRespuestaDeError(mensajeError);
        }
        return respuestaControlador;
    }

    private List<ObjetosMenuResponseDTO> obtenerMenuUsuarioLogueado(){
        List<ObjetosMenuResponseDTO> objetosMenu = new ArrayList<>();

        ObjetosMenuResponseDTO menu1 = new ObjetosMenuResponseDTO();
        menu1.setNombre("operario");
        menu1.setUrl("/operario");
        objetosMenu.add(menu1);

        ObjetosMenuResponseDTO menu2 = new ObjetosMenuResponseDTO();
        menu2.setNombre("vehiculo");
        menu2.setUrl("/vehiculo");
        objetosMenu.add(menu2);

        ObjetosMenuResponseDTO menu3 = new ObjetosMenuResponseDTO();
        menu3.setNombre("pedido");
        menu3.setUrl("/pedido");
        objetosMenu.add(menu3);

        return objetosMenu;
    }

}
