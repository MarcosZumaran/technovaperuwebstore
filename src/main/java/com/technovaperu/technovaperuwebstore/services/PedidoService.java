package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface PedidoService {
    List<Map<String, Object>> obtenerPedidosPorUsuario(int idUsuario, int pagina);
    Map<String, Object> obtenerPedidoPorId(int id);
    String crearPedido(Map<String, Object> pedido);
    String actualizarPedido(int id, Map<String, Object> pedido);
    String eliminarPedido(int id);
    int contarPedidosPorUsuario(int idUsuario);
    int contarPedidos();
}
