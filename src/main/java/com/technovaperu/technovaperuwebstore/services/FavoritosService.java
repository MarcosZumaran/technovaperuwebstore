package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.FavoritosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearFavoritoDTO;

public interface FavoritosService {
    List<FavoritosDTO> obtenerFavoritosPorUsuario(int idUsuario, int pagina);
    FavoritosDTO obtenerFavoritoPorId(int id);
    FavoritosDTO crearFavorito(CrearFavoritoDTO favorito);
    void eliminarFavorito(int id);
    int contarFavoritos();
    boolean existeFavoritoPorId(int id);
}
