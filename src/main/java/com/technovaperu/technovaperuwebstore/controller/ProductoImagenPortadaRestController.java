package com.technovaperu.technovaperuwebstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.services.ProductoImagenPortadaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/productoImagenPortada")
public class ProductoImagenPortadaRestController {

    @Autowired
    private ProductoImagenPortadaService productoImagenPortadaService;

    @GetMapping("/getImagenPortadaByProducto/{id}")
    public Map<String, Object> getImagenPortadaByProducto(@PathVariable int id){
        return productoImagenPortadaService.obtenerImagenPortadaPorProducto(id);
    }

    @GetMapping("/getImagenPortada/{id}")
    public Map<String, Object> getImagenPortadaById(@PathVariable int id){
        return productoImagenPortadaService.obtenerImagenPortadaPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> imagen){
        return productoImagenPortadaService.crearImagenPortada(imagen);
    }

    @PostMapping("/updateImagenPortada/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> imagen){
        return productoImagenPortadaService.actualizarImagenPortada(id, imagen);
    }

    @DeleteMapping("/deleteImagenPortada/{id}")
    public String delete(@PathVariable int id){
        return productoImagenPortadaService.eliminarImagenPortada(id);
    }

    @RequestMapping("/countImagenPortada")
    public int count(){
        return productoImagenPortadaService.contarImagenesPortada();
    }
    
}
