package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.services.FavoritosService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/favoritos")
public class FavoritosRestController {

    @Autowired
    private FavoritosService favoritosService;

    @GetMapping("/getFavoritosByUser/{id}")
    public List<Map<String, Object>> getFavoritosByUser(@RequestParam int id, @RequestParam(defaultValue = "1") int pagina) {
        return favoritosService.obtenerFavoritosPorUsuario(id, pagina);
    }

    @GetMapping("/getFavorito/{id}")
    public Map<String, Object> getFavorito(@RequestParam int id) {
        return favoritosService.obtenerFavoritoPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> favorito) {
        return favoritosService.crearFavorito(favorito);
    }

    @PostMapping("/updateFavorito/{id}")
    public String update(@RequestParam int id, @RequestBody Map<String, Object> favorito) {
        return favoritosService.actualizarFavorito(id, favorito);
    }

    @DeleteMapping("/deleteFavorito/{id}")
    public String delete(@RequestParam int id) {
        return favoritosService.eliminarFavorito(id);
    }
    
    @GetMapping("/countFavoritos")
    public int count() {
        return favoritosService.contarFavoritos();
    }
    
}
