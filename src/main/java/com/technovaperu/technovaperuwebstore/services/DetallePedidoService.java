package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.DetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearDetallePedidoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedidoDTO> obtenerDetallesPorPedido(int idPedido);
    Page<DetallePedidoDTO> obtenerDetallesPorPedidoPaginados(int idPedido, int pagina, int tamanoPagina); // Paginación añadida
    DetallePedidoDTO obtenerDetallePorId(int id);
    DetallePedidoDTO crearDetallePedido(CrearDetallePedidoDTO dto);
    void eliminarDetallePedido(int id);
}
