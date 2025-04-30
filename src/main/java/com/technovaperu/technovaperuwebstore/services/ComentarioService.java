package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.ComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarComentarioDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComentarioService {
    List<ComentarioDTO> obtenerComentariosPorProducto(int idProducto);
    Page<ComentarioDTO> obtenerComentariosPorProductoPaginados(int idProducto, int pagina, int tamanoPagina); // Paginación añadida
    ComentarioDTO obtenerComentarioPorId(int id);
    ComentarioDTO crearComentario(CrearComentarioDTO dto);
    ComentarioDTO actualizarComentario(int id, ActualizarComentarioDTO dto);
    void eliminarComentario(int id);
}
