package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.services.ProductoImagenService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/productoImagen")
public class ProductoImagenRestController {
    
    @Autowired
    private ProductoImagenService productoImagenService;

    @GetMapping("/getImagenesPorProducto/{id}")
    public List<Map<String, Object>> getImagenesPorProducto(@PathVariable int id) {
        return productoImagenService.obtenerImagenesPorProducto(id);
    }

    @GetMapping("/getImagen/{id}")
    public Map<String, Object> getImagen(@PathVariable int id) {
        return productoImagenService.obtenerImagenPorId(id);
    }

    @PostMapping("/createImagen")
    public String create(@RequestBody Map<String, Object> imagen) {
        return productoImagenService.crearImagen(imagen);
    }

    @PostMapping("/updateImagen/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> imagen) {
        return productoImagenService.actualizarImagen(id, imagen);
    }

    @DeleteMapping("/deleteImagen/{id}")
    public String delete(@PathVariable int id) {
        return productoImagenService.eliminarImagen(id);
    }

    @GetMapping("/countImagenesPorProducto/{id}")
    public int countImagenesPorProducto(@PathVariable int id) {
        return productoImagenService.contarImagenesPorProducto(id);
    }

    @GetMapping("/countImagenes")
    public int count() {
        return productoImagenService.contarImagenes();
    }

}
