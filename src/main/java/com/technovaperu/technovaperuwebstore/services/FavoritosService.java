package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.FavoritosDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FavoritosService {
    List<FavoritosDTO> obtenerFavoritosPorUsuario(int idUsuario);
    Page<FavoritosDTO> obtenerFavoritosPorUsuarioPaginados(int idUsuario, int pagina, int tamanoPagina); // Paginación añadida
    FavoritosDTO agregarFavorito(int idUsuario, int idProducto);
    void eliminarFavorito(int idUsuario, int idProducto);
}
