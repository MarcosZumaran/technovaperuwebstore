package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProveedorDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO> listarProveedores();
    Page<ProveedorDTO> listarProveedoresPaginados(int pagina, int tamanoPagina); // Paginación añadida
    ProveedorDTO obtenerProveedorPorId(int id);
    ProveedorDTO crearProveedor(CrearProveedorDTO dto);
    ProveedorDTO actualizarProveedor(int id, ActualizarProveedorDTO dto);
    void eliminarProveedor(int id);
}
