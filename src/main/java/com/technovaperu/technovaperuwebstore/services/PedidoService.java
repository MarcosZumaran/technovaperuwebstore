package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.PedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearPedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarPedidoDTO;

public interface PedidoService {

    List<PedidoDTO> obtenerTodosLosPedidos();
    List<PedidoDTO> obtenerPedidos(int pagina);
    List<PedidoDTO> obtenerPedidosPorUsuario(int pagina, long idUsuario);
    List<PedidoDTO> obtenerPedidosPorUsuarioPorEstado(int pagina, long idUsuario, String estado);
    List<PedidoDTO> obtenerPedidosPorEstado(int pagina, String estado);
    PedidoDTO obtenerPedidoPorId(long id);
    PedidoDTO crearPedido(CrearPedidoDTO pedidoDTO);
    void actualizarPedido(long id, ActualizarPedidoDTO pedidoDTO);
    void borrarPedido(long id);
    boolean existePedido(long id);
    long contarPedidos();

}