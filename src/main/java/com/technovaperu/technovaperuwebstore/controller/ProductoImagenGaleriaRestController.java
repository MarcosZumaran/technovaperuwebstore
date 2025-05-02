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

import com.technovaperu.technovaperuwebstore.services.ProductoImagenGaleriaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/productoImagenGaleria")
public class ProductoImagenGaleriaRestController {

    @Autowired
    private ProductoImagenGaleriaService productoImagenGaleriaService;

    @GetMapping("/getImagenGaleriaByProducto/{id}")
    public List<Map<String, Object>> getImagenesGaleriaByProducto(@PathVariable int id){
        return productoImagenGaleriaService.obtenerImagenesGaleriaPorProducto(id);
    } 

    @GetMapping("/getImagenGaleria/{id}")
    public Map<String, Object> getImagenGaleriaById(@PathVariable int id){
        return productoImagenGaleriaService.obtenerImagenGaleriaPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> imagen){
        return productoImagenGaleriaService.crearImagenGaleria(imagen);
    }

    @PostMapping("/updateImagenGaleria/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> imagen){
        return productoImagenGaleriaService.actualizarImagenGaleria(id, imagen);
    }

    @DeleteMapping("/deleteImagenGaleria/{id}")
    public String delete(@PathVariable int id){
        return productoImagenGaleriaService.eliminarImagenGaleria(id);
    }

    @GetMapping("/countImagenGaleriaByProducto/{id}")
    public int countByProducto(@PathVariable int id){
        return productoImagenGaleriaService.contarImagenesGaleriaPorProducto(id);
    }

    @GetMapping("/countImagenGaleria")
    public int count(){
        return productoImagenGaleriaService.contarImagenesGaleria();
    }
    
}
