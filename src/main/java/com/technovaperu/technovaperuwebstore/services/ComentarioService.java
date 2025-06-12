package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarComentarioDTO;

public interface ComentarioService {
    List<ComentarioDTO> obtenerComentariosPorProducto(long idProducto, int pagina);
    ComentarioDTO obtenerComentarioPorId(long id);
    ComentarioDTO crearComentario(CrearComentarioDTO comentario);
    void actualizarComentario(long id, ActualizarComentarioDTO comentario);
    void eliminarComentario(long id);
    int contarComentariosDeProducto(long idProducto);
    int contarComentarios();
    boolean existeComentarioPorId(long id);
}
