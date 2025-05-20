package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
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

    @GetMapping("/producto/{id}")
    public ResponseEntity<ApiResponse<List<ProductoImagenDTO>>> obtenerImagenesPorProducto(@PathVariable int id) {
        List<ProductoImagenDTO> imagenes = productoImagenService.obtenerImagenesPorProducto(id);
        return ResponseEntity.ok(ApiResponse.success(imagenes, "Imagenes obtenidas con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoImagenDTO>> obtenerImagenPorId(@PathVariable int id) {
        ProductoImagenDTO imagen = productoImagenService.obtenerImagenPorId(id);
        return ResponseEntity.ok(ApiResponse.success(imagen, "Imagen obtenida con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductoImagenDTO>> crearImagen(@RequestBody CrearProductoImagenDTO imagen) {
        ProductoImagenDTO imagenCreada = productoImagenService.crearImagen(imagen);
        return new ResponseEntity<>(
            ApiResponse.success(imagenCreada, "Imagen creada con éxito"),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoImagenDTO>> actualizarImagen(@PathVariable int id, @RequestBody ActualizarProductoImagenDTO imagen) {
        productoImagenService.actualizarImagen(id, imagen);
        return ResponseEntity.ok(ApiResponse.success(null, "Imagen actualizada con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarImagen(@PathVariable int id) {
        productoImagenService.eliminarImagen(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Imagen eliminada con éxito"));
    }

    @GetMapping("/count/producto/{id}")
    public ResponseEntity<ApiResponse<Integer>> contarImagenesPorProducto(@PathVariable int id) {
        int count = productoImagenService.contarImagenesPorProducto(id);
        return ResponseEntity.ok(ApiResponse.success(count, "Total de imagenes obtenido con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarImagenes() {
        int count = productoImagenService.contarImagenes();
        return ResponseEntity.ok(ApiResponse.success(count, "Total de imagenes obtenido con éxito"));
    }

}
