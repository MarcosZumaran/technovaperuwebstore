package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.ProductoService;

@RestController
@RequestMapping("/api/producto")
public class ProductoRestController {

    private final ProductoService productoService;

    @Autowired
    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> obtenerTodosLosProductos(@RequestParam(defaultValue = "1") int page){
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos(page);
        return ResponseEntity.ok(ApiResponse.success(productos, "Productos obtenidos con éxito"));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> obtenerProductosPorCategoria(@RequestParam int id, @RequestParam(defaultValue = "1") int page){
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(id, page);
        return ResponseEntity.ok(ApiResponse.success(productos, "Productos obtenidos con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoDTO>> obtenerProductoPorId(@RequestParam int id){
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(ApiResponse.success(producto, "Producto obtenido con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductoDTO>> crearProducto(@RequestParam CrearProductoDTO producto){
        ProductoDTO productoCreado = productoService.crearProducto(producto);
        return new ResponseEntity<>(
            ApiResponse.success(productoCreado, "Producto creado con éxito"),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoDTO>> actualizarProducto(@RequestParam int id, @RequestParam ActualizarProductoDTO producto){
        productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(ApiResponse.success(null, "Producto actualizado con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarProducto(@RequestParam int id){
        productoService.eliminarProducto(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Producto eliminado con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarProductos(){
        int count = productoService.contarProductos();
        return ResponseEntity.ok(ApiResponse.success(count, "Total de productos obtenido con éxito"));
    }
}
