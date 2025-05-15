package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.HistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearHistorialPedidosDTO;
public interface HistorialPedidosService {
    List<HistorialPedidosDTO> obtenerHistorialPorPedido(int idPedido, int pagina);
    HistorialPedidosDTO obtenerHistorialPorId(int id);
    HistorialPedidosDTO crearHistorialPedido(CrearHistorialPedidosDTO historial);
    void eliminarHistorialPedido(int id);
    int contarHistorialPedidos();
    boolean existeHistorialPedidoPorId(int id);
}
