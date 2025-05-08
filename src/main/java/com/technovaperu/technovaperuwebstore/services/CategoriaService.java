package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.CategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarCategoriaDTO;

public interface CategoriaService {
    List<CategoriaDTO> obtenerTodasLasCategorias();
    CategoriaDTO obtenerCategoriaPorId(int id);
    CategoriaDTO crearCategoria(CrearCategoriaDTO categoria);
    CategoriaDTO actualizarCategoria(int id, ActualizarCategoriaDTO categoria);
    void eliminarCategoria(int id);
    int contarCategorias();
    boolean existeCategoriaPorId(int id);
}