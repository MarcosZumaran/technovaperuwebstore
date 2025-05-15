package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.services.ProductoImagenService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/producto-imagen")
public class ProductoImagenRestController {
    
    private final ProductoImagenService productoImagenService;

    @Autowired
    public ProductoImagenRestController(ProductoImagenService productoImagenService) {
        this.productoImagenService = productoImagenService;
    }

    @GetMapping("/getImagenesPorProducto/{id}")
    public ResponseEntity<List<ProductoImagenDTO>> obtenerImagenesPorProducto(@PathVariable int id) {
        List<ProductoImagenDTO> imagenes = productoImagenService.obtenerImagenesPorProducto(id);
        return ResponseEntity.ok(imagenes);
    }

    @GetMapping("/getImagen/{id}")
    public ResponseEntity<ProductoImagenDTO> obtenerImagenPorId(@PathVariable int id) {
        ProductoImagenDTO imagen = productoImagenService.obtenerImagenPorId(id);
        return ResponseEntity.ok(imagen);
    }

    @PostMapping("/createImagen")
    public ResponseEntity<ProductoImagenDTO> crearImagen(@RequestBody CrearProductoImagenDTO imagen) {
        ProductoImagenDTO imagenCreada = productoImagenService.crearImagen(imagen);
        return new ResponseEntity<>(imagenCreada, HttpStatus.CREATED);
    }

    @PostMapping("/updateImagen/{id}")
    public ResponseEntity<ProductoImagenDTO> update(@PathVariable int id, @RequestBody ActualizarProductoImagenDTO imagen) {
        productoImagenService.actualizarImagen(id, imagen);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteImagen/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        productoImagenService.eliminarImagen(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countImagenesPorProducto/{id}")
    public ResponseEntity<Integer> countImagenesPorProducto(@PathVariable int id) {
        int count = productoImagenService.contarImagenesPorProducto(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countImagenes() {
        int count = productoImagenService.contarImagenes();
        return ResponseEntity.ok(count);
    }

}
