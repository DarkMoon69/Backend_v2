package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.entity.Cliente;
import com.example.pedidobackend.entity.DetallePedido;
import com.example.pedidobackend.entity.EstadoPedido;
import com.example.pedidobackend.entity.Operario;
import com.example.pedidobackend.entity.Pedido;
import com.example.pedidobackend.entity.Producto;
import com.example.pedidobackend.entity.TipoDocumento;
import com.example.pedidobackend.entity.Vehiculo;
import com.example.pedidobackend.enums.EstadoPedidoEnum;
import com.example.pedidobackend.repository.ClienteRepository;
import com.example.pedidobackend.repository.DetallePedidoRepository;
import com.example.pedidobackend.repository.PedidoRepository;
import com.example.pedidobackend.repository.ProductoRepository;
import com.example.pedidobackend.service.PedidoService;
import com.example.pedidobackend.util.RespuestaControlador;
import com.example.pedidobackend.util.dto.BusquedaResponseDTO;
import com.example.pedidobackend.util.dto.PedidoBusquedaRequestDTO;
import com.example.pedidobackend.util.dto.PedidoRequestDTO;
import com.example.pedidobackend.util.dto.ProductoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    RespuestaControladorServicio respuestaControladorServicio;

    @Override
    public PedidoRequestDTO getById(Long id) {
        PedidoRequestDTO requestDTO = new PedidoRequestDTO();

        Pedido pedido = pedidoRepository.getReferenceById(id);
        requestDTO.setClienteId(pedido.getCliente().getId());
        requestDTO.setTipoDocumentoId(pedido.getCliente().getTipoDocumento().getId());
        requestDTO.setNumeroDocumento(pedido.getCliente().getNumeroDocumento());
        requestDTO.setCelular(pedido.getCliente().getNumeroCelular());
        requestDTO.setApePaterno(pedido.getCliente().getApePaterno());
        requestDTO.setApeMaterno(pedido.getCliente().getApeMaterno());
        requestDTO.setNombre(pedido.getCliente().getNombres());
        requestDTO.setDireccion(pedido.getCliente().getDireccion());
        requestDTO.setEmail(pedido.getCliente().getCorreoElectronico());
        if (pedido.getOperario() != null) {
            requestDTO.setOperarioId(pedido.getOperario().getId());
        }
        if (pedido.getVehiculo() != null) {
            requestDTO.setVehiculoId(pedido.getVehiculo().getId());
        }
        if (pedido.getFechaPedido() != null) {
            requestDTO.setFechaPedido(pedido.getFechaPedido());
        }

        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoIdAndEstadoTrue(id);
        List<ProductoRequestDTO> listado = new ArrayList<>();
        for (DetallePedido detallePedido: detalles) {
            ProductoRequestDTO productoRequestDTO = new ProductoRequestDTO();
            productoRequestDTO.setProductoId(detallePedido.getProducto().getId());
            productoRequestDTO.setProducto(detallePedido.getProducto().getNombre());
            productoRequestDTO.setCodigo(detallePedido.getProducto().getCodigoSku());
            productoRequestDTO.setCantidad(detallePedido.getCantidad());
            listado.add(productoRequestDTO);
        }
        requestDTO.setProductos(listado);

        return requestDTO;
    }

    @Override
    public BusquedaResponseDTO busquedaPaginada(PedidoBusquedaRequestDTO dto) {
        List<Map<String, Object>> data = pedidoRepository.busquedaPaginadaPedido(dto.getCliente(), dto.getEstadoPedidoId(), dto.getOperarioId(), dto.getMax(), dto.getLimite());
        Integer cantidadTotal = pedidoRepository.busquedaPaginadaPedidoContar(dto.getCliente(), dto.getEstadoPedidoId(), dto.getOperarioId(), dto.getMax(), dto.getLimite());
        BusquedaResponseDTO responseDTO = new BusquedaResponseDTO();
        responseDTO.setData(data);
        responseDTO.setPaginaActual(dto.getLimite());
        responseDTO.setTotalRegistros(cantidadTotal);
        responseDTO.setCantidadPorPagina( dto.getMax());
        return responseDTO;
    }

    @Override
    public RespuestaControlador guardar(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente();
        TipoDocumento tipoDocumento = new TipoDocumento();

        EstadoPedido estadoPedido = new EstadoPedido();
        if (pedidoRequestDTO.getOperarioId() != null) {
            estadoPedido.setId(EstadoPedidoEnum.ASIGNADO.getId());
        } else {
            estadoPedido.setId(EstadoPedidoEnum.PENDIENTE.getId());
        }

        if (pedidoRequestDTO.getClienteId() > 0L) {
            cliente.setId(pedidoRequestDTO.getClienteId());
        } else {
            tipoDocumento.setId(pedidoRequestDTO.getTipoDocumentoId());
            cliente.setApePaterno(pedidoRequestDTO.getApePaterno());
            cliente.setApeMaterno(pedidoRequestDTO.getApeMaterno());
            cliente.setNombres(pedidoRequestDTO.getNombre());
            cliente.setNombrecompleto(pedidoRequestDTO.getApePaterno() + ' ' + pedidoRequestDTO.getApeMaterno() + ' ' + pedidoRequestDTO.getNombre());
            cliente.setTipoDocumento(tipoDocumento);
            cliente.setNumeroDocumento(pedidoRequestDTO.getNumeroDocumento());
            cliente.setNumeroCelular(pedidoRequestDTO.getCelular());
            cliente.setDireccion(pedidoRequestDTO.getDireccion());
            cliente.setCorreoElectronico(pedidoRequestDTO.getEmail());
            clienteRepository.save(cliente);
        }
        pedido.setCliente(cliente);
        if (pedidoRequestDTO.getOperarioId() != null) {
            Operario operario = new Operario();
            operario.setId(pedidoRequestDTO.getOperarioId());
            pedido.setOperario(operario);
        }
        if (pedidoRequestDTO.getVehiculoId() != null) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setId(pedidoRequestDTO.getVehiculoId());
            pedido.setVehiculo(vehiculo);
        }
        if (pedidoRequestDTO.getFechaPedido() != null) {
            pedido.setFechaPedido(pedidoRequestDTO.getFechaPedido());
        }
        pedido.setEstadoPedido(estadoPedido);
        pedido.setDireccionEntrega(pedidoRequestDTO.getDireccion());

        pedidoRepository.save(pedido);

        for (ProductoRequestDTO productoRequestDTO: pedidoRequestDTO.getProductos()) {
            DetallePedido detallePedido = new DetallePedido();
            Producto producto = new Producto();
            if (productoRequestDTO.getProductoId() > 0L) {
                producto.setId(productoRequestDTO.getProductoId());
            } else {
                producto.setNombre(productoRequestDTO.getProducto());
                producto.setCodigoSku(productoRequestDTO.getCodigo());
                productoRepository.save(producto);
            }
            detallePedido.setPedido(pedido);
            detallePedido.setProducto(producto);
            detallePedido.setCantidad(productoRequestDTO.getCantidad());
            detallePedidoRepository.save(detallePedido);
        }

        RespuestaControlador respuestaControlador = respuestaControladorServicio.obtenerRespuestaDeExitoCrear("Pedido");
        respuestaControlador.setExtraInfo(pedido.getId());
        return respuestaControlador;
    }

    @Override
    public RespuestaControlador editar(Long pedidoId, PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = pedidoRepository.getReferenceById(pedidoId);

        EstadoPedido estadoPedido = new EstadoPedido();
        if (pedidoRequestDTO.getOperarioId() != null) {
            estadoPedido.setId(EstadoPedidoEnum.ASIGNADO.getId());
        } else {
            estadoPedido.setId(EstadoPedidoEnum.PENDIENTE.getId());
        }
        if (pedidoRequestDTO.getOperarioId() != null) {
            Operario operario = new Operario();
            operario.setId(pedidoRequestDTO.getOperarioId());
            pedido.setOperario(operario);
        }
        if (pedidoRequestDTO.getVehiculoId() != null) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setId(pedidoRequestDTO.getVehiculoId());
            pedido.setVehiculo(vehiculo);
        }
        if (pedidoRequestDTO.getFechaPedido() != null) {
            pedido.setFechaPedido(pedidoRequestDTO.getFechaPedido());
        }
        pedido.setEstadoPedido(estadoPedido);
        pedidoRepository.save(pedido);

        RespuestaControlador respuestaControlador = respuestaControladorServicio.obtenerRespuestaDeExitoActualizar("Pedido");
        respuestaControlador.setExtraInfo(pedido.getId());
        return respuestaControlador;
    }

    @Override
    public RespuestaControlador eliminar(Long id) {
        RespuestaControlador respuestaControlador;
        EstadoPedido estadoPedido = new EstadoPedido();
        estadoPedido.setId(EstadoPedidoEnum.ANULADO.getId());
        Pedido pedido = pedidoRepository.getReferenceById(id);
        pedido.setEstadoPedido(estadoPedido);
        pedidoRepository.save(pedido);
        respuestaControlador = respuestaControladorServicio.obtenerRespuestaDeExitoAnular("Pedido");
        return respuestaControlador;
    }
}
