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

import com.technovaperu.technovaperuwebstore.services.CategoriaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/categorias")
public class CategoriaRestController {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Map<String, Object>> getAll(){
        return categoriaService.obtenerTodasLasCategorias();
    }

    @GetMapping("/getCategoria/{id}")
    public Map<String, Object> getCategoria(@PathVariable int id){
        return categoriaService.obtenerCategoriaPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> categoria){
        return categoriaService.crearCategoria(categoria);
    }

    @PostMapping("/updateCategoria/{id}")
    public String update (@PathVariable int id, @RequestBody Map<String, Object> categoria){
        return categoriaService.actualizarCategoria(id, categoria);
    }

    @DeleteMapping("/deleteCategoria/{id}")
    public String delete (@PathVariable int id){
        return categoriaService.eliminarCategoria(id);
    }

    @GetMapping("/countCategorias")
    public int count(){
        return categoriaService.contarCategorias();
    }

}
