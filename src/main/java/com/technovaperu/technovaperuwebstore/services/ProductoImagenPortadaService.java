package com.technovaperu.technovaperuwebstore.services;

import java.util.Map;

public interface ProductoImagenPortadaService {
    Map<String, Object> obtenerImagenPortadaPorProducto(int idProducto);
    Map<String, Object> obtenerImagenPortadaPorId(int id);
    String crearImagenPortada(Map<String, Object> imagen);
    String actualizarImagenPortada(int id, Map<String, Object> imagen);
    String eliminarImagenPortada(int id);
    int contarImagenesPortada();
}
