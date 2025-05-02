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

import com.technovaperu.technovaperuwebstore.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Map<String, Object>> getAll(@RequestParam(defaultValue = "1") int page){
        return productoService.obtenerTodosLosProductos(page);
    }

    @GetMapping("/getProductoByCategoria/{id}")
    public List<Map<String, Object>> getProductoByCategoria(@RequestParam int id, @RequestParam(defaultValue = "1") int page){
        return productoService.obtenerProductosPorCategoria(id, page);
    }

    @PostMapping
    public String create(@RequestParam Map<String, Object> producto){
        return productoService.crearProducto(producto);
    }

    @PostMapping("/updateProducto/{id}")
    public String update(@RequestParam int id, @RequestParam Map<String, Object> producto){
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/deleteProducto/{id}")
    public String delete(@RequestParam int id){
        return productoService.eliminarProducto(id);
    }

    @GetMapping("/getProducto/{id}")
    public Map<String, Object> getProducto(@RequestParam int id){
        return productoService.obtenerProductoPorId(id);
    }

    @GetMapping("/countProductos")
    public int count(){
        return productoService.contarProductos();
    }
    
}
