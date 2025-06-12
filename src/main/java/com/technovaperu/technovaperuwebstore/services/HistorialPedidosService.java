package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.HistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearHistorialPedidosDTO;
public interface HistorialPedidosService {
    List<HistorialPedidosDTO> obtenerHistorialPedidos();
    List<HistorialPedidosDTO> obtenerHistorialPedidoPorUsuario(int pagina,long idUsuario);
    List<HistorialPedidosDTO> obtenerHistorialPedidoPorPedido(int pagina, long idPedido);
    HistorialPedidosDTO obtenerHistorialPedidoPorId(long id);
    HistorialPedidosDTO crearHistorialPedido(CrearHistorialPedidosDTO historialPedidoDTO);
    void borrarHistorialPedido(long id);
    boolean existeHistorialPedido(long id);
}
