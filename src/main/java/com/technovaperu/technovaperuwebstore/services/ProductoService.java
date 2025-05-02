package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface ProductoService {
    List<Map<String, Object>> obtenerTodosLosProductos(int pagina);
    List<Map<String, Object>> obtenerProductosPorCategoria(int idCategoria, int pagina);
    Map<String, Object> obtenerProductoPorId(int id);
    String crearProducto(Map<String, Object> producto);
    String actualizarProducto(int id, Map<String, Object> producto);
    String eliminarProducto(int id);
    int contarProductosPorCategoria(int idCategoria);
    int contarProductos();
}
