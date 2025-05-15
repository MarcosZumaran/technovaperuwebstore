package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.PedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearPedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarPedidoDTO;

public interface PedidoService {
    List<PedidoDTO> obtenerPedidosPorUsuario(int idUsuario, int pagina);
    PedidoDTO obtenerPedidoPorId(int id);
    PedidoDTO crearPedido(CrearPedidoDTO pedido);
    void actualizarPedido(int id, ActualizarPedidoDTO pedido);
    void eliminarPedido(int id);
    int contarPedidosPorUsuario(int idUsuario);
    int contarPedidos();
    boolean existePedidoPorId(int id);
}
