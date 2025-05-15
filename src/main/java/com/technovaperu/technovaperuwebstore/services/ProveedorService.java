package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProveedorDTO;

public interface ProveedorService {
    List<ProveedorDTO> obtenerTodosLosProveedores(int pagina);
    ProveedorDTO obtenerProveedorPorId(int id);
    ProveedorDTO crearProveedor(CrearProveedorDTO proveedor);
    void actualizarProveedor(int id, ActualizarProveedorDTO proveedor);
    void eliminarProveedor(int id);
    int contarProveedores();
    boolean existeProveedorPorId(int id);
}
