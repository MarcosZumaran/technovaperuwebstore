package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarComentarioDTO;

public interface ComentarioService {
    List<ComentarioDTO> obtenerComentariosPorProducto(int idProducto, int pagina);
    ComentarioDTO obtenerComentarioPorId(int id);
    ComentarioDTO crearComentario(CrearComentarioDTO comentario);
    void actualizarComentario(int id, ActualizarComentarioDTO comentario);
    void eliminarComentario(int id);
    int contarComentariosDeProducto(int idProducto);
    int contarComentarios();
    boolean existeComentarioPorId(int id);
}
