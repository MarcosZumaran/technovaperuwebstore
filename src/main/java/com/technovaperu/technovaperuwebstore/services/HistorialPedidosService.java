package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;
public interface HistorialPedidosService {
    List<Map<String, Object>> obtenerHistorialPorPedido(int idPedido, int pagina);
    Map<String, Object> obtenerHistorialPorId(int id);
    String crearHistorialPedido(Map<String, Object> historial);
    String actualizarHistorialPedido(int id, Map<String, Object> historial);
    String eliminarHistorialPedido(int id);
    int contarHistorialPedidos();
}
