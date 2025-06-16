package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.Operario;
import com.example.pedidobackend.entity.Usuario;
import com.example.pedidobackend.repository.OperarioRepository;
import com.example.pedidobackend.repository.UsuarioRepository;
import com.example.pedidobackend.service.EmailService;
import com.example.pedidobackend.service.OperarioService;
import com.example.pedidobackend.util.Constantes;
import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.OperarioBusquedaRequestDTO;
import com.example.pedidobackend.util.dto.BusquedaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@Service
public class OperarioServiceImpl implements OperarioService {

    @Autowired
    private OperarioRepository operarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    RespuestaControladorServicio respuestaControladorServicio;

    @Autowired
    EmailService emailService;

    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+{}[]";

    private static final int LONGITUD_CLAVE = 15;

    @Override
    public RespuestaControlador guardar(Operario operario) {
        operario.setCargo("OPERARIO");
        operario.setNombrecompleto(operario.getApePaterno() + ' ' + operario.getApeMaterno() + ' ' + operario.getNombres());
        operario.setEstadoOperario(Constantes.OPERARIO_ACTIVO);
        operarioRepository.save(operario);
        Usuario usuario = new Usuario();
        usuario.setOperario(operario);
        usuario.setNombreUsuario(generarUsuario(operario.getNombres(), operario.getApePaterno(), operario.getApeMaterno()));
        usuario.setContraseniaHash(generarClaveAleatoria());
        usuario.setRol("OPERARIO");
        usuarioRepository.save(usuario);

        String mensaje = "Estimado(a) " + operario.getApePaterno() + " " + operario.getApeMaterno() + " " + operario.getNombres() +
                ", se le envían sus credenciales para el sistema: \n " +
                " Login : " + usuario.getNombreUsuario() + "\n"+
                " Contraseña : " + usuario.getContraseniaHash();

        emailService.enviarCorreo(operario.getCorreoElectronico(), "Credenciales Creadas", mensaje);

        RespuestaControlador respuestaControlador = respuestaControladorServicio.obtenerRespuestaDeExitoCrear("Operario");
        respuestaControlador.setExtraInfo(operario.getId());
        return respuestaControlador;
    }

    @Override
    public RespuestaControlador actualizar(Operario operario) {
        RespuestaControlador respuestaControlador;
        operarioRepository.save(operario);
        respuestaControlador = respuestaControladorServicio.obtenerRespuestaDeExitoActualizar("Operario");
        respuestaControlador.setExtraInfo(operario.getId());
        return respuestaControlador;
    }

    @Override
    public RespuestaControlador eliminar(Long id) {
        RespuestaControlador respuestaControlador;
        Operario operario = operarioRepository.getReferenceById(id);
        operario.setEstadoOperario(Constantes.OPERARIO_INACTIVO);
        operarioRepository.save(operario);
        respuestaControlador = respuestaControladorServicio.obtenerRespuestaDeExitoEliminar("Operario");
        return respuestaControlador;
    }

    @Override
    public Operario getById(Long id) {
        return operarioRepository.findById(id).get();
    }

    @Override
    public BusquedaResponseDTO busquedaPaginada(OperarioBusquedaRequestDTO dto) {
        List<Map<String, Object>> data = operarioRepository.busquedaPaginadaOperario(dto.getNombre(), dto.getApellidoPaterno(), dto.getApellidoMaterno(), dto.getTipoDocumentoId(),
                dto.getNumeroDocumento(), dto.getEstadoOperario(), dto.getMax(), dto.getLimite());
        Integer cantidadTotal = operarioRepository.busquedaPaginadaOperarioContar(dto.getNombre(), dto.getApellidoPaterno(), dto.getApellidoMaterno(), dto.getTipoDocumentoId(),
                dto.getNumeroDocumento(), dto.getEstadoOperario(), dto.getMax(), dto.getLimite());
        BusquedaResponseDTO responseDTO = new BusquedaResponseDTO();
        responseDTO.setData(data);
        responseDTO.setPaginaActual(dto.getLimite());
        responseDTO.setTotalRegistros(cantidadTotal);
        responseDTO.setCantidadPorPagina( dto.getMax());
        return responseDTO;
    }

    @Override
    public List<Operario> obtenerTodos() {
        return operarioRepository.findByEstadoTrue();
    }

    private String generarUsuario(String nombre, String apepat, String apemat) {
        String inicialNombre = nombre.substring(0, 2).toUpperCase();
        String inicialApemat = apemat.substring(0, 2).toUpperCase();
        return inicialNombre + apepat.toUpperCase() + inicialApemat;
    }

    private String generarClaveAleatoria() {
        SecureRandom random = new SecureRandom();
        StringBuilder clave = new StringBuilder();

        // Genera cada carácter de la clave
        for (int i = 0; i < LONGITUD_CLAVE; i++) {
            int indice = random.nextInt(CARACTERES.length());
            char caracter = CARACTERES.charAt(indice);
            clave.append(caracter);
        }

        return clave.toString();
    }

}
