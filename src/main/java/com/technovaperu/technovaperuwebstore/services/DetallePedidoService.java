package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface DetallePedidoService {
    
    List<Map<String, Object>> obtenerDetallesPedidoPorPedido(int idPedido, int pagina);
    Map<String, Object> obtenerDetallePedidoPorId(int id);
    String crearDetallePedido(Map<String, Object> detalle);
    String actualizarDetallePedido(int id, Map<String, Object> detalle);
    String eliminarDetallePedido(int id);
    int contarDetallesPedido();
}
