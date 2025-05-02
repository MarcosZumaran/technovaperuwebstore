package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface ItemCarritoService {
    List<Map<String, Object>> obtenerItemsCarritoPorCarrito(int idCarrito, int pagina);
    Map<String, Object> obtenerItemCarritoPorId(int id);
    String crearItemCarrito(Map<String, Object> item);
    String actualizarItemCarrito(int id, Map<String, Object> item);
    String eliminarItemCarrito(int id);
    int contarItemsCarrito();
}
