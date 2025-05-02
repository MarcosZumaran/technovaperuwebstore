package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface ProductoImagenGaleriaService {
    List<Map<String, Object>> obtenerImagenesGaleriaPorProducto(int id);
    Map<String, Object> obtenerImagenGaleriaPorId(int id);
    String crearImagenGaleria(Map<String, Object> imagen);
    String actualizarImagenGaleria(int id, Map<String, Object> imagen);
    String eliminarImagenGaleria(int id);
    int contarImagenesGaleriaPorProducto(int id);
    int contarImagenesGaleria();
}
