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

    @GetMapping("/getFavoritosByUser/{id}")
    public ResponseEntity<List<FavoritosDTO>> obtenerFavoritosPorUsuario(int id) {
        List<FavoritosDTO> favoritos = favoritosService.obtenerFavoritosPorUsuario(id, 1);
        return ResponseEntity.ok(favoritos);
    }

    @GetMapping("/getFavorito/{id}")
    public ResponseEntity<FavoritosDTO> obtenerFavoritoPorId(int id) {
        FavoritosDTO favorito = favoritosService.obtenerFavoritoPorId(id);
        return ResponseEntity.ok(favorito);
    }

    @PostMapping
    public ResponseEntity<FavoritosDTO> crearFavorito(@RequestBody CrearFavoritoDTO favorito) {
        FavoritosDTO favoritoCreado = favoritosService.crearFavorito(favorito);
        return new ResponseEntity<>(favoritoCreado, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteFavorito/{id}")
    public ResponseEntity<Void> eliminarFavorito(int id) {
        favoritosService.eliminarFavorito(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public ResponseEntity<Integer> contarFavoritos() {
        int cantidad = favoritosService.contarFavoritos();
        return ResponseEntity.ok(cantidad);
    }
    
}
