package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.CategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarCategoriaDTO;

public interface CategoriaService {

    List<CategoriaDTO> obtenerTodasLasCategorias();
    List<CategoriaDTO> obtenerCategorias(int pagina);
    CategoriaDTO obtenerCategoriaPorId(long id);
    CategoriaDTO obtenerCategoriaPorProducto(long idProducto);
    CategoriaDTO crearCategoria(CrearCategoriaDTO categoriaDTO);
    void actualizarCategoria(long id, ActualizarCategoriaDTO categoriaDTO);
    void borrarCategoria(long id);
    boolean existeCategoria(long id);
}
