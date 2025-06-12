package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.FavoritosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearFavoritoDTO;

public interface FavoritosService {
    List<FavoritosDTO> obtenerFavoritosPorUsuario(long idUsuario, int pagina);
    FavoritosDTO obtenerFavoritoPorId(long id);
    FavoritosDTO crearFavorito(CrearFavoritoDTO favorito);
    void eliminarFavorito(long id);
    int contarFavoritos();
    boolean existeFavoritoPorId(long id);
}
