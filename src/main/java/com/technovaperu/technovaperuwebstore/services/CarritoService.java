package com.technovaperu.technovaperuwebstore.services;

import java.util.Map;

public interface CarritoService {

    Map<String, Object> obtenerCarritoPorId(int id);
    String crearCarrito(Map<String, Object> carrito);
    String actualizarCarrito(int id, Map<String, Object> carrito);
    String eliminarCarrito(int id);
    int contarCarritos();
    
}
