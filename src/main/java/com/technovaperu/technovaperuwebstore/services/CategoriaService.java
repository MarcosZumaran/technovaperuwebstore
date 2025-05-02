package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface CategoriaService {
    
    List<Map<String, Object>> obtenerTodasLasCategorias();
    Map<String, Object> obtenerCategoriaPorId(int id);
    String crearCategoria(Map<String, Object> categoria);
    String actualizarCategoria(int id, Map<String, Object> categoria);
    String eliminarCategoria(int id);
    int contarCategorias();

}
