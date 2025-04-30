package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.CategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarCategoriaDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> listarCategorias();
    Page<CategoriaDTO> listarCategoriasPaginadas(int pagina, int tamanoPagina); // Paginación añadida
    CategoriaDTO obtenerCategoriaPorId(int id);
    CategoriaDTO crearCategoria(CrearCategoriaDTO dto);
    CategoriaDTO actualizarCategoria(int id, ActualizarCategoriaDTO dto);
    void eliminarCategoria(int id);
}
