package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProveedorDTO;

public interface ProveedorService {

    List<ProveedorDTO> obtenerProveedores(int pagina);
    List<ProveedorDTO> obtenerTodosLosProveedores();
    List<ProveedorDTO> obtenerProveedoresPorNombre(String nombre);
    List<ProveedorDTO> obtenerProveedoresPorActivo(boolean activo);
    ProveedorDTO obtenerProveedorPorId(long id);
    ProveedorDTO obtenerProveedorPorRuc(String ruc);
    ProveedorDTO crearProveedor(CrearProveedorDTO proveedorDTO);
    void actualizarProveedor(long id, ActualizarProveedorDTO proveedorDTO);
    void borrarProveedor(long id);
    boolean existeProveedor(long id);
    
}