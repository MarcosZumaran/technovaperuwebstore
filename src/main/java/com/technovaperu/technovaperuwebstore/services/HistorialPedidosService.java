package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.HistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearHistorialPedidosDTO;
import org.springframework.data.domain.Page;

import java.util.List;
public interface HistorialPedidosService {
    List<HistorialPedidosDTO> obtenerHistorialPorPedido(int idPedido);
    Page<HistorialPedidosDTO> obtenerHistorialPorPedidoPaginados(int idPedido, int pagina, int tamanoPagina); // Paginación añadida
    HistorialPedidosDTO obtenerHistorialPorId(int id);
    HistorialPedidosDTO crearHistorialPedido(CrearHistorialPedidosDTO dto);
    void eliminarHistorialPedido(int id);
}
