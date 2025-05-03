package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface ProductoImagenService {

    List<Map<String, Object>> obtenerImagenesPorProducto(int id);
    Map<String, Object> obtenerImagenPorId(int id);
    String crearImagen(Map<String, Object> imagen);
    String actualizarImagen(int id, Map<String, Object> imagen);
    String eliminarImagen(int id);
    int contarImagenesPorProducto(int id);
    int contarImagenes();
    
}
