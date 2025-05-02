package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface UsuarioService {
    List<Map<String, Object>> obtenerTodosLosUsuarios(int pagina);
    Map<String, Object> obtenerUsuarioPorId(int id);
    int obtenerCarritoDeUsuario(int idUsuario);
    String crearUsuario(Map<String, Object> usuario);
    String actualizarUsuario(int id, Map<String, Object> usuario);
    String eliminarUsuario(int id);
    int contarUsuarios();
}
