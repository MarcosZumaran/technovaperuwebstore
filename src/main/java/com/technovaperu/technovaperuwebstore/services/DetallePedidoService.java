package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.DetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearDetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarDetallePedidoDTO;

public interface DetallePedidoService {
    List<DetallePedidoDTO> obtenerDetallePedidos();
    List<DetallePedidoDTO> obtenerDetallesPedidoPorPedido(int pagina,long pedidoId);
    DetallePedidoDTO obtenerDetallePedidoPorId(long id);
    DetallePedidoDTO crearDetallePedido(CrearDetallePedidoDTO detallePedidoDTO);
    void actualizarDetallePedido(long id, ActualizarDetallePedidoDTO detallePedidoDTO);
    void borrarDetallePedido(long id);
    boolean existeDetallePedido(long id);
}
