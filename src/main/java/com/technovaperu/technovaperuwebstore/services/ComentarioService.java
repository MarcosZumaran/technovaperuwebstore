package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface ComentarioService {
    List<Map<String, Object>> obtenerComentariosPorProducto(int idProducto, int pagina);
    Map<String, Object> obtenerComentarioPorId(int id);
    String crearComentario(Map<String, Object> comentario);
    String actualizarComentario(int id, Map<String, Object> comentario);
    String eliminarComentario(int id);
    int contarComentariosDeProducto(int idProducto);
    int contarComentarios();
}
