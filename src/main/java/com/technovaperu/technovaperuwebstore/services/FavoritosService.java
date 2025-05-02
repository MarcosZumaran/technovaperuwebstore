package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface FavoritosService {
    List<Map<String, Object>> obtenerFavoritosPorUsuario(int idUsuario, int pagina);
    Map<String, Object> obtenerFavoritoPorId(int id);
    String crearFavorito(Map<String, Object> favorito);
    String actualizarFavorito(int id, Map<String, Object> favorito);
    String eliminarFavorito(int id);
    int contarFavoritos();
}
