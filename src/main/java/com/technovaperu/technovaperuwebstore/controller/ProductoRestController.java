package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoDTO;
import com.technovaperu.technovaperuwebstore.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoRestController {

    private final ProductoService productoService;

    @Autowired
    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos(@RequestParam(defaultValue = "1") int page){
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos(page);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/getProductoByCategoria/{id}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@RequestParam int id, @RequestParam(defaultValue = "1") int page){
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(id, page);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/getProducto/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@RequestParam int id){
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestParam CrearProductoDTO producto){
        ProductoDTO productoCreado = productoService.crearProducto(producto);
        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    @PostMapping("/updateProducto/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestParam int id, @RequestParam ActualizarProductoDTO producto){
        productoService.actualizarProducto(id, producto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteProducto/{id}")
    public ResponseEntity<Void> eliminarProducto(@RequestParam int id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countProductos")
    public ResponseEntity<Integer> contarProductos(){
        int count = productoService.contarProductos();
        return ResponseEntity.ok(count);
    }
}
