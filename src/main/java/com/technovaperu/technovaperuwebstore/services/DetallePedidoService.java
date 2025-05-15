package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.DetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearDetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarDetallePedidoDTO;

public interface DetallePedidoService {
    
    List<DetallePedidoDTO> obtenerDetallesPedidoPorPedido(int idPedido, int pagina);
    DetallePedidoDTO obtenerDetallePedidoPorId(int id);
    DetallePedidoDTO crearDetallePedido(CrearDetallePedidoDTO detalle);
    void actualizarDetallePedido(int id, ActualizarDetallePedidoDTO detalle);
    void eliminarDetallePedido(int id);
    int contarDetallesPedido();
    boolean existeDetallePedidoPorId(int id);
}
