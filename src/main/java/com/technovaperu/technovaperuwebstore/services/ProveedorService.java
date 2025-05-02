package com.technovaperu.technovaperuwebstore.services;

import java.util.List;
import java.util.Map;

public interface ProveedorService {
    List<Map<String, Object>> obtenerTodosLosProveedores();
    Map<String, Object> obtenerProveedorPorId(int id);
    String crearProveedor(Map<String, Object> proveedor);
    String actualizarProveedor(int id, Map<String, Object> proveedor);
    String eliminarProveedor(int id);
    int contarProveedores();
}
