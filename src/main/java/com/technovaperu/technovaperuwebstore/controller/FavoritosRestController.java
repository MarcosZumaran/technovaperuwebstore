package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.FavoritosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearFavoritoDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.FavoritosService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/favorito")
public class FavoritosRestController {

    private final FavoritosService favoritosService;

    @Autowired
    public FavoritosRestController(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<ApiResponse<List<FavoritosDTO>>> obtenerFavoritosPorUsuario(int id) {
        List<FavoritosDTO> favoritos = favoritosService.obtenerFavoritosPorUsuario(id, 1);
        return ResponseEntity.ok(ApiResponse.success(favoritos, "Favoritos obtenidos con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FavoritosDTO>> obtenerFavoritoPorId(int id) {
        FavoritosDTO favorito = favoritosService.obtenerFavoritoPorId(id);
        return ResponseEntity.ok(ApiResponse.success(favorito, "Favorito obtenido con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FavoritosDTO>> crearFavorito(@RequestBody CrearFavoritoDTO favorito) {
        FavoritosDTO favoritoCreado = favoritosService.crearFavorito(favorito);
        return new ResponseEntity<>(
            ApiResponse.success(favoritoCreado, "Favorito creado con éxito"),
            HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarFavorito(int id) {
        favoritosService.eliminarFavorito(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Favorito eliminado con éxito"));
    }
    
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarFavoritos() {
        int cantidad = favoritosService.contarFavoritos();
        return ResponseEntity.ok(ApiResponse.success(cantidad, "Total de favoritos obtenido con éxito"));
    }
    
}
